package cn.xuyangl.onlineshopping.controller;

import cn.xuyangl.onlineshopping.VO.Result;
import cn.xuyangl.onlineshopping.VO.ResultEnum;
import cn.xuyangl.onlineshopping.consts.Common;
import cn.xuyangl.onlineshopping.model.ShopDetailForm;
import cn.xuyangl.onlineshopping.service.ShopService;
import cn.xuyangl.onlineshopping.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author: lxy
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/12/3 11:24
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/shops",method = RequestMethod.GET)
    public Result findAllShops(@RequestParam(name = "pageNo",required = false,defaultValue = "0") String pageNo
            , @RequestParam(name="pageSize",required = false,defaultValue = "10")String  pageSize)
    {
        return ResultUtil.success(shopService.findAllShops(Integer.parseInt(pageNo),Integer.parseInt(pageSize)));
    }

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

    // 买家查看店铺详情页（带收藏标识）
    @GetMapping("/{shopId}")
    public Result buyerViewShop(@PathVariable("shopId") Integer shopId, HttpSession session) {
        Integer buyerId = (Integer) session.getAttribute(Common.BUYER_ID);
        return ResultUtil.success(shopService.buyerViewShop(shopId, buyerId));
    }


}
