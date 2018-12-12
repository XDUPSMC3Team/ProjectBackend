package cn.xuyangl.onlineshopping.service.impl;

import cn.xuyangl.onlineshopping.service.FtpService;
import cn.xuyangl.onlineshopping.utils.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:39
 */
@Service
public class FtpServiceImpl implements FtpService{

    @Value("${ftp.address}")
    private String ADDRESS;

    @Value("${ftp.port}")
    private int PORT;

    @Value("${ftp.username}")
    private String USERNAME;

    @Value("${ftp.password}")
    private String PASSWORD;

    @Value("${ftp.basepath}")
    private String BASEPATH;

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile multipartFile)
    {
        String originName = multipartFile.getOriginalFilename();
        System.out.println(originName);
        String ext = originName.split("\\.")[1];
        String prefix = UUID.randomUUID().toString();
        String name = prefix+"."+ext;
//        return FtpUtil.uploadFile(multipartFile,ADDRESS,PORT,USERNAME,PASSWORD,BASEPATH, name, FTPClient.BINARY_FILE_TYPE);
        return FtpUtil.uploadLocalFile(multipartFile,BASEPATH,name);
    }

}
