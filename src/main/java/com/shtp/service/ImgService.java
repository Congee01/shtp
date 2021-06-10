package com.shtp.service;

import com.shtp.vo.ImgInfoVO;
import com.shtp.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface ImgService {
    ResultVO<ImgInfoVO> uploadFile(MultipartFile file);
}
