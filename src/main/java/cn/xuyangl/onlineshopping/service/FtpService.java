package cn.xuyangl.onlineshopping.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:38
 */
public interface FtpService {
    /**
     *  图片上传
     * @param multipartFile
     * @return
     */
     String uploadFile(MultipartFile multipartFile);
}
