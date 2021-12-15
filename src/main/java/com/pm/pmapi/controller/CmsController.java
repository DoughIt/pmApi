package com.pm.pmapi.controller;

import cn.hutool.core.util.StrUtil;
import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.common.utils.FormDataUtil;
import com.pm.pmapi.common.utils.UploadUtil;
import com.pm.pmapi.component.IAuthenticationFacade;
import com.pm.pmapi.dto.CommodityInfo;
import com.pm.pmapi.dto.CommodityParam;
import com.pm.pmapi.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    UploadUtil uploadUtil = new UploadUtil();
    FormDataUtil formDataUtil = new FormDataUtil();
    /**
     * 获取PPT信息
     */
    @RequestMapping(value = "/ppt", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getPPT(@RequestParam(value = "id") Long id) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.getCommodityById(userId, id));
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getBook(@RequestParam(value = "id") Long id) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());

        return CommonResult.success(commodityService.getCommodityById(userId, id));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getNotes(@RequestParam(value = "id") Long id) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());

        return CommonResult.success(commodityService.getCommodityById(userId, id));
    }

    @RequestMapping(value = "/ppts", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getPPTs(@RequestParam(value = "key") String key, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.listCommoditiesByTypeAndKey(userId, 1, key, pageNum, pageSize));
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getBooks(@RequestParam(value = "key") String key, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.listCommoditiesByTypeAndKey(userId, 2, key, pageNum, pageSize));
    }

    @RequestMapping(value = "/notses", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getNotses(@RequestParam(value = "key") String key, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.listCommoditiesByTypeAndKey(userId, 3, key, pageNum, pageSize));
    }


    @RequestMapping(value = "/ppt", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addPPT(@RequestParam("picture")MultipartFile file, @RequestParam("lessonId") String lessonId, @RequestParam("chapters") String chapters, @RequestParam("paperSize") String paperSize,
                                       @RequestParam("singlePrint") boolean singlePrint, @RequestParam("newDegree") String newDegree, @RequestParam("price") Double price, @RequestParam("content") String content) {
        if (null == file || file.isEmpty()){
            return CommonResult.failed("请选择图片");
        }
        String url = uploadUtil.uploadFile(file,null);
        if (StrUtil.isEmptyOrUndefined(url)){
            return CommonResult.failed("上传失败");
        }
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        CommodityParam commodityParam = new CommodityParam();
        commodityParam.setImageUrl(url);
        commodityParam.setSellerId(userId);
        commodityParam.setLessonId(lessonId);
        commodityParam.setSinglePrint(singlePrint);
        commodityParam.setNewDegree(newDegree);
        commodityParam.setPaperSize(paperSize);
        commodityParam.setPrice(price);
        commodityParam.setContent(content);
        commodityParam.setChapters(chapters);
        return CommonResult.success(commodityService.createCommodity(Long.parseLong(authenticationFacade.getAuthentication().getName()), 1, commodityParam));
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addBooks(@RequestParam("picture")MultipartFile file, @RequestParam("lessonId") String lessonId, @RequestParam("name") String name, @RequestParam("author") String author,
                                         @RequestParam("publisher") String publisher, @RequestParam("newDegree") String newDegree, @RequestParam("price") Double price, @RequestParam("content") String content) {
        if (null == file || file.isEmpty()){
            return CommonResult.failed("请选择图片");
        }
        String url = uploadUtil.uploadFile(file,null);
        if (StrUtil.isEmptyOrUndefined(url)){
            return CommonResult.failed("上传失败");
        }
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        CommodityParam commodityParam = new CommodityParam();
        commodityParam.setImageUrl(url);
        commodityParam.setSellerId(userId);
        commodityParam.setLessonId(lessonId);
        commodityParam.setName(name);
        commodityParam.setAuthor(author);
        commodityParam.setContent(content);
        commodityParam.setPublisher(publisher);
        commodityParam.setNewDegree(newDegree);
        commodityParam.setPrice(price);
        return CommonResult.success(commodityService.createCommodity(Long.parseLong(authenticationFacade.getAuthentication().getName()), 2, commodityParam));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addNotes(@RequestParam("picture")MultipartFile file, @RequestParam("lessonId") String lessonId, @RequestParam("coverPercentage") String coverPercentage,
                                         @RequestParam("price") Double price, @RequestParam("content") String content) {
        if (null == file || file.isEmpty()){
            return CommonResult.failed("请选择图片");
        }
        String url = uploadUtil.uploadFile(file,null);
        if (StrUtil.isEmptyOrUndefined(url)){
            return CommonResult.failed("上传失败");
        }
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        CommodityParam commodityParam = new CommodityParam();
        commodityParam.setImageUrl(url);
        commodityParam.setSellerId(userId);
        commodityParam.setLessonId(lessonId);
        commodityParam.setPrice(price);
        commodityParam.setContent(content);
        commodityParam.setCoverPercentage(coverPercentage);
        return CommonResult.success(commodityService.createCommodity(Long.parseLong(authenticationFacade.getAuthentication().getName()), 3, commodityParam));
    }

    @RequestMapping(value = "/ppt", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult<Object> updatePPT(@RequestBody CommodityParam commodityParam) {
        Long id = commodityParam.getId();
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.updateCommodityByPrimaryKey(userId, id, commodityParam));
    }

    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult<Object> updateBook(@RequestBody CommodityParam commodityParam) {
        Long id = commodityParam.getId();
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.updateCommodityByPrimaryKey(userId, id, commodityParam));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult<Object> updateNotes(@RequestBody CommodityParam commodityParam) {
        Long id = commodityParam.getId();
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.updateCommodityByPrimaryKey(userId, id, commodityParam));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<Object> deleteNotes(@RequestParam(value = "id") Long id) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        commodityService.deleteCommodityById(userId, id);
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<Object> deleteBook(@RequestParam(value = "id") Long id) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        commodityService.deleteCommodityById(userId, id);
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/ppt", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<Object> deletePPT(@RequestParam(value = "id") Long id) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        commodityService.deleteCommodityById(userId, id);
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/commodities", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getCommodities(@RequestParam(value = "isMine") Boolean isMine, @RequestParam(value = "type") Integer type, @RequestParam(value = "isSold") Boolean isSold, @RequestParam(value = "lessonId") String lessonId, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        Long userId = null;
        if (isMine) {
            userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        }
        return CommonResult.success(commodityService.getCommodities(userId, type, lessonId, isSold, isMine, pageNum, pageSize));
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addFavorite(@RequestParam(value = "id") Long commodity_id) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.addFavoriteCommodity(userId, commodity_id));
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<Object> deleteFavorite(@RequestParam(value = "id") Long commodity_id) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.deleteFavoriteCommodity(userId, commodity_id));

    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getFavorites() {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(commodityService.listFavoriteCommodities(userId));
    }
}
