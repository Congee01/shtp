package com.shtp.controller;

import com.shtp.service.ImgService;
import com.shtp.vo.ImgInfoVO;
import com.shtp.vo.ResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class ImgController {
    @Resource
    ImgService ImgService;
    @Value("${web.file-upload-path}")
    private String path;
    @PostMapping("/upload")
    public ResultVO<ImgInfoVO> upload(@RequestParam("file") MultipartFile file) {
        return ImgService.uploadImg(file);
    }


}
