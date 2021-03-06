package cn.xuyangl.onlineshopping.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.nntp.NNTP;
import org.aspectj.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:37
 */
public class FtpUtil {

    // OS
//    final static String REMOTE_RELATIVE_PATH = "/Users/mac/Downloads/images/";
    // window
//    final static String REMOTE_RELATIVE_PATH = "/images/";
    // 远程
    final static String REMOTE_RELATIVE_PATH = "http://119.23.75.180/";

    // 远程
    final static String LOCAL_PATH = "/home/ftptest/productPic/";
    // mac os
//    final static String LOCAL_PATH = "/Users/mac/Downloads/images/";
    // windows
//    final static String LOCAL_PATH = "D:/images/";

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
    public static String uploadLocalFile(MultipartFile multipartFile,String basePath,String name)
    {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        File file = new File(LOCAL_PATH + simpleDateFormat.format(now) +"/"+name);

        if (!file.exists())
        {
            File parentFile =file.getParentFile();
            if (!parentFile.exists())
            {
                parentFile.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();
            byte[] bytes = new byte[1024];
            int index;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while ((index=inputStream.read(bytes))!=-1)
            {
                fileOutputStream.write(bytes,0,index);
                fileOutputStream.flush();
            }
            return REMOTE_RELATIVE_PATH+simpleDateFormat.format(now)+"/"+name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
