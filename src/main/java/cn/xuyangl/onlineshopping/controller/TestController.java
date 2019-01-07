package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xjin
 * created on 2019/1/4 11:54 PM
 */
@RestController
@RequestMapping("/db")
public class TestController {

    @GetMapping("/backup")
    @ResponseBody
    public Result databaseBackUp(HttpServletResponse response) throws UnsupportedEncodingException {
        // 生成备份SQL
        String savePath = "/root/test/";
        String dbName = "shopping";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String timeStr = sdf.format(now);
        String filePath = savePath + dbName + timeStr + ".sql";
        System.out.println("fileName:"+filePath);
        try {
            Process pr = Runtime.getRuntime().exec(
                    "mysqldump -u root -p1234 " + dbName + " -r " + filePath
            );
            // 获取进程的输出流（相对于内存是输入流）
            InputStream is = pr.getInputStream();
            // 获取进程的报错输出流
            InputStream isError = pr.getErrorStream();
            // 编码格式设定
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            InputStreamReader isrError = new InputStreamReader(isError, "UTF-8");
            // 读取进程输出的信息
            BufferedReader br = new BufferedReader(isr);
            BufferedReader brError = new BufferedReader(isrError);
            // 控制台打印信息
            String info = "";
            while ((info = br.readLine()) != null) {
                System.out.println(info);
            }
            // 控制台打印报错信息
            String error = "";
            while ((error = brError.readLine()) != null) {
                System.out.println(error);
            }
            // 关闭流
            br.close();
            brError.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("success dump sql.");
//        return ResultUtil.success();

        File file = new File(filePath);
        if (file.exists()) {
            String sqlFileName = file.getName();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(sqlFileName, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the sql file successfully!");
                return ResultUtil.success();
            }
            catch (Exception e) {
                System.out.println("Download the sql file failed!");
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return ResultUtil.error(ResultEnum.FileDonotExist);
        }
        return ResultUtil.error(-1, "unknown error in download file");
    }
}
