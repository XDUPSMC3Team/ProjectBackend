package cn.xuyangl.onlineshopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author xjin
 * created on 2019/1/4 11:54 PM
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/backup")
    public String databaseBackUp(HttpServletResponse response) throws IOException {
        String filePath = "/root/test/";
        String dbName = "shopping";
        Process process = Runtime.getRuntime().exec(
                "/bin/sh mysqldump -u root -p 1234 " + dbName + " > "
                + filePath + dbName + new Date().getTime() + ".sql"
        );
        return "success";
    }
}
