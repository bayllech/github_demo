package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTableResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException.ServiceException;
import com.kaishengit.pojo.*;
import com.kaishengit.service.DeviceService;
import com.kaishengit.service.WorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private WorkTypeService workTypeService;

    @GetMapping
    public String list(Model model) {
        List<DeviceRent> rentList = deviceService.findAllRent();
        model.addAttribute("rentList", rentList);
        return "/device/rent/list";
    }

    /**
     * 租赁合同详情页
     * @param request
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public DataTableResult list(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String q_serialNum = request.getParameter("serialNum");

        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("start", start);
        queryParam.put("length", length);
        queryParam.put("q_serialNum", q_serialNum);
        System.out.println("q_serialNum:" + q_serialNum);
        System.out.println("start:"+ start);
        System.out.println("length:"+length);

        List<DeviceRent> rentList = deviceService.findRentByQueryParam(queryParam);
        Long count = deviceService.countDeviceRent();
        Long filterCount = deviceService.filterCountRent(queryParam);

        return new DataTableResult(draw,count,filterCount,rentList);
    }

    /**
     * 新增租赁信息
     * @return 新增租赁页面
     */
    @GetMapping("/new")
    public String newRent(Model model) {
        List<Device> deviceList = deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
        return "/device/rent/new";
    }

    /**
     * 保存合同
     * @param deviceRentDto 设备与合同信息
     * @return
     */
    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveRent(@RequestBody DeviceRentDto deviceRentDto) {
        try{
            String serialNumber = deviceService.saveRent(deviceRentDto);
            return new AjaxResult(AjaxResult.SUCCESS, serialNumber);
        } catch (ServiceException e){
            return new AjaxResult(AjaxResult.ERROR, e.getMessage());
        }


    }

    /**
     * 根据设备ID查找设备信息
     * @param id
     * @return 设备信息
     */
    @GetMapping("/device.json")
    @ResponseBody
    public AjaxResult deviceJson(Integer id) {
        Device device = deviceService.findDeviceById(id);
        if(device == null) {
            return new AjaxResult(AjaxResult.ERROR,"设备不存在");
        } else {
            return new AjaxResult(device);
        }
    }

    /**
     * 根据流水号查找合同
     * @param serialNumber
     * @param model
     * @return 合同详情页
     */
    @GetMapping("/{serialNumber:\\d+}")
    public String showRent(@PathVariable String serialNumber,Model model) {
        DeviceRent deviceRent = deviceService.findRentBySerialNum(serialNumber);
        if (deviceRent == null) {
            throw new NotFoundException();
        } else {
            List<DeviceRentDetail> rentDetailList = deviceService.findRentDetailByRentId(deviceRent.getId());
            List<WorkTypeDetail> workTypeDetailList = workTypeService.findRentDetailByRentId(deviceRent.getId());
            List<DeviceRentDoc> docList = deviceService.findDocByRentId(deviceRent.getId());

            model.addAttribute("rent", deviceRent);
            model.addAttribute("detailList", rentDetailList);
            model.addAttribute("workTypeList", workTypeDetailList);
            model.addAttribute("docList", docList);

            return "/device/rent/show";
        }

    }

    /**
     * 下载文档
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/doc")
    public ResponseEntity<InputStreamResource> loadDoc(Integer id) throws IOException {
        InputStream inputStream = deviceService.loadDocById(id);
        if (inputStream == null) {
            throw new NotFoundException();
        } else {
            DeviceRentDoc doc = deviceService.findDocById(id);
            String fileName = doc.getSourceName();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment", fileName, Charset.forName("UTF-8"));

            return new ResponseEntity<>(new InputStreamResource(inputStream), httpHeaders, HttpStatus.OK);
        }
    }

    /**
     * 打包下载
     * @param id
     */
    @GetMapping("/doc/zip")
    public void zipLoadDoc(Integer id,HttpServletResponse response) throws IOException {
        DeviceRent rent = deviceService.findRentById(id);
        if (rent == null) {
            throw new NotFoundException();
        } else {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            String fileName = rent.getCompanyName()+".zip";
            fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            deviceService.loadZipDocs(rent,zipOutputStream);

        }
    }

    /**
     * 根据文档id查找文档并下载
     * @param id
     * @param response
     */
   /* @GetMapping("/doc")
    public void loadDoc(Integer id, HttpServletResponse response) throws IOException {
        InputStream inputStream = deviceService.loadDocById(id);
        if (inputStream == null) {
            throw new NotFoundException();
        } else {
            DeviceRentDoc doc = deviceService.findDocById(id);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            String fileName = doc.getSourceName();
            fileName = new String(fileName.getBytes("UTF-8"), "iso8859-1");
            response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }
    }
*/

   @PostMapping("/state/change")
   @ResponseBody
   public String changeState(Integer id) {
       DeviceRent rent = deviceService.findRentById(id);
       rent.setState("已完成");
       deviceService.updateRentState(rent);
       return "success";
   }

}
