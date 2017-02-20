package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDoc;
import com.kaishengit.service.DeviceService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list(Model model) {
        List<DeviceRent> rentList = deviceService.findAllRent();
        model.addAttribute("rentList", rentList);
        return "/device/rent/list";
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
        String serialNumber = deviceService.saveRent(deviceRentDto);
        return new AjaxResult(AjaxResult.SUCCESS, serialNumber);

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
            List<DeviceRentDoc> docList = deviceService.findDocByRentId(deviceRent.getId());

            model.addAttribute("rent", deviceRent);
            model.addAttribute("detailList", rentDetailList);
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

}
