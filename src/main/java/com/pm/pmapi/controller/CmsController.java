package com.pm.pmapi.controller;

import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.component.IAuthenticationFacade;
import com.pm.pmapi.dto.CommodityParam;
import com.pm.pmapi.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: pmApi
 * @description:
 * @author: Shen Zhengyu
 * @create: 2021-12-07 13:35
 **/
@Controller
@RequestMapping("/api/cms")
public class CmsController {

    @Autowired
    CommodityServiceImpl commodityService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    /**
     * 获取PPT信息
     */
    @RequestMapping(value = "/ppt", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getPPT(@RequestParam(value = "id") Long id) {
        return CommonResult.success(commodityService.getCommodityById(id));
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getBook(@RequestParam(value = "id") Long id) {
        return CommonResult.success(commodityService.getCommodityById(id));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getNotes(@RequestParam(value = "id") Long id) {
        return CommonResult.success(commodityService.getCommodityById(id));
    }

    @RequestMapping(value = "/ppts", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getPPTs(@RequestParam(value = "key") String key, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        return CommonResult.success(commodityService.listCommoditiesByTypeAndKey(1, key, pageNum, pageSize));
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getBooks(@RequestParam(value = "key") String key, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        return CommonResult.success(commodityService.listCommoditiesByTypeAndKey(2, key, pageNum, pageSize));
    }

    @RequestMapping(value = "/notses", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getNotses(@RequestParam(value = "key") String key, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        return CommonResult.success(commodityService.listCommoditiesByTypeAndKey(3, key, pageNum, pageSize));
    }


    @RequestMapping(value = "/ppt", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addPPT(@RequestBody CommodityParam commodityParam) {
        return CommonResult.success(commodityService.createCommodity(Long.parseLong(authenticationFacade.getAuthentication().getName()),1 , commodityParam));
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addBooks(@RequestBody CommodityParam commodityParam) {
        return CommonResult.success(commodityService.createCommodity(Long.parseLong(authenticationFacade.getAuthentication().getName()), 2,commodityParam));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addNotes(@RequestBody CommodityParam commodityParam) {
        return CommonResult.success(commodityService.createCommodity(Long.parseLong(authenticationFacade.getAuthentication().getName()), 3,commodityParam));
    }

    @RequestMapping(value = "/ppt", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult<Object> updatePPT(@RequestBody CommodityParam commodityParam) {
        Long id = commodityParam.getId();
        return CommonResult.success(commodityService.updateCommodityByPrimaryKey(id, commodityParam));
    }

    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult<Object> updateBook(@RequestBody CommodityParam commodityParam) {
        Long id = commodityParam.getId();
        return CommonResult.success(commodityService.updateCommodityByPrimaryKey(id, commodityParam));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult<Object> updateNotes(@RequestBody CommodityParam commodityParam) {
        Long id = commodityParam.getId();
        return CommonResult.success(commodityService.updateCommodityByPrimaryKey(id, commodityParam));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<Object> deleteNotes(@RequestParam(value = "id") Long id) {
        commodityService.deleteCommodityById(id);
        return CommonResult.success(null);
    }
    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<Object> deleteBook(@RequestParam(value = "id") Long id) {
        commodityService.deleteCommodityById(id);
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/ppt", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<Object> deletePPT(@RequestParam(value = "id") Long id) {
        commodityService.deleteCommodityById(id);
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/commodities", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getCommoditiesByUserId(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        return CommonResult.success(commodityService.getSoldCommodityByUserId(Long.parseLong(authenticationFacade.getAuthentication().getName()),pageNum,pageSize));
    }
}
