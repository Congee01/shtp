package com.shtp.serviceimpl;

import com.shtp.exception.MyException;
import com.shtp.service.ImgService;
import com.shtp.util.Constant;
import com.shtp.util.ImgHelper;
import com.shtp.vo.ImgInfoVO;
import com.shtp.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImgServiceImpl implements ImgService {

    private static final Logger logger = LoggerFactory.getLogger(ImgService.class);

    @Value("${web.file-upload-path}")
    private String path;

    @Override
    public ResultVO<ImgInfoVO> uploadImg(MultipartFile file) {
        try {
            ImgInfoVO ImgInfoVO = ImgHelper.save(path, file);
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "图片上传成功", ImgInfoVO);
        } catch (MyException myException){
            logger.error("用于存放上传图片的文件夹不存在或创建失败，请检查路径是否有效", myException);
        } catch (IOException ioException){
            logger.error("图片复制时出错", ioException);
        }
        return new ResultVO<>(-1, "服务器错误，请联系网站管理员。");
    }
}
