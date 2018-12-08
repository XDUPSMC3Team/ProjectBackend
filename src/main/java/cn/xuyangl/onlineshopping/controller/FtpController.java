package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.service.FtpService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 图片上传控制器
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/6 09:30
 */
@RestController
@RequestMapping("/")
public class FtpController {

    @Autowired
    private FtpService ftpService;

    @RequestMapping(value = "/pic/upload",method = RequestMethod.POST)
    public Result uploadPic(@RequestParam("file") MultipartFile multipartfile)
    {
        return ResultUtil.success(ftpService.uploadFile(multipartfile));
    }
}
