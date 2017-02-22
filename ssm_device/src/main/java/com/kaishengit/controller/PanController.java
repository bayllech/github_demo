package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.ServiceException.ServiceException;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


}
