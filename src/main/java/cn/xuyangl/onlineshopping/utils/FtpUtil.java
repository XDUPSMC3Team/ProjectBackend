package cn.xuyangl.onlineshopping.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:37
 */
public class FtpUtil {

    final static String REMOTE_RELATIVE_PATH = "119.23.75.180/";

    public static String uploadFile(MultipartFile multipartFile, String address, int port,
                                     String username, String password, String basePath, String name, int fileType) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(address, port);
            ftpClient.login(username,password);

            //更改图片保存路径
            ftpClient.changeWorkingDirectory(basePath);

            /**
             * 按照年月日的文件夹目录进行保存
             */
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String[] paths = simpleDateFormat.format(now).split("/");

            for (String s:paths)
            {
                ftpClient.makeDirectory(s);
                ftpClient.changeWorkingDirectory(s);
            }
            ftpClient.setFileType(fileType);
            ftpClient.storeFile(name,multipartFile.getInputStream());
            return REMOTE_RELATIVE_PATH+simpleDateFormat.format(now)+"/"+name;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
