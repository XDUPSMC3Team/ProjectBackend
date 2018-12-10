package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.model.ShopDetailForm;
import cn.xuyangl.onlineshopping.service.ShopService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/3 11:24
 */
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     *  更改商家信息 hop contacts / Email / Telephone
     * @param shopDetailForm
     * @return
     */
    @PostMapping
    @RequestMapping("/shopDetail/{shopId}")
    public Result modifyShopDetail(@PathVariable String shopId,@RequestBody ShopDetailForm shopDetailForm)
    {
        boolean b = shopService.modifyShopDetail(Integer.parseInt(shopId), shopDetailForm);

        if (b)
        {
            return ResultUtil.success();
        }else{
            return ResultUtil.error(ResultEnum.SHOP_NOT_FOUND);
        }
    }

}
