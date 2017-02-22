package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.exception.ServiceException.ServiceException;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/pan")
public class PanController {

    @Autowired
    private DiskService diskService;

    @GetMapping
    public String list(@RequestParam(required = false,defaultValue = "0") Integer path, Model model) {
        List<Disk> diskList = diskService.findByFid(path);
        model.addAttribute("diskList", diskList);
        model.addAttribute("fid", path);
        return "/pan/list";
    }

    /**
     * 新建文件夹
     * @param disk
     * @return
     */
    @PostMapping("/folder/new")
    @ResponseBody
    public String list(Disk disk) {
        diskService.saveFolder(disk);
        return "success";
    }

    /**
     * 文件上传
     * @param fid
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(Integer fid, MultipartFile file) {
        try {
            diskService.saveFile(fid, file);
            return new AjaxResult(AjaxResult.SUCCESS);
        } catch (ServiceException ex) {
            return new AjaxResult(AjaxResult.ERROR,ex.getMessage());
        }

    }

    /**
     * 下载文件
     * @param id
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void dowloand(Integer id, HttpServletResponse response) throws IOException {
        InputStream inputStream = diskService.findById(id);
        if (inputStream == null) {
            throw new NotFoundException();
        } else {
            Disk disk = diskService.findDiskById(id);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            String fileName = disk.getSourceName();
            fileName = new String(fileName.getBytes("utf-8"), "iso8859-1");

            response.setHeader("Content-Disposition","filename=\""+fileName+"\"");
            OutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }
    }

    @PostMapping("/remove")
    @ResponseBody
    public String remove(Integer id) {
        diskService.delById(id);
        return "success";
    }


}
