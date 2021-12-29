package com.pm.pmapi.controller;

import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.pm.pmapi.common.api.CommonPage;
import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.common.utils.FormDataUtil;
import com.pm.pmapi.common.utils.UploadUtil;
import com.pm.pmapi.component.IAuthenticationFacade;
import com.pm.pmapi.dto.*;
import com.pm.pmapi.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public CommonResult<Object> getPPTs(@RequestParam(value = "key") Optional<String> key, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(CommonPage.restPage(commodityService.listCommoditiesByTypeAndKey(userId, 1, key.orElse(""), pageNum, pageSize)));
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getBooks(@RequestParam(value = "key") Optional<String> key, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(CommonPage.restPage(commodityService.listCommoditiesByTypeAndKey(userId, 2, key.orElse(""), pageNum, pageSize)));
    }

    @RequestMapping(value = "/noteses", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getNoteses(@RequestParam(value = "key") Optional<String> key, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        return CommonResult.success(CommonPage.restPage(commodityService.listCommoditiesByTypeAndKey(userId, 3, key.orElse(""), pageNum, pageSize)));
    }


    @RequestMapping(value = "/ppt", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addPPT(@RequestBody PPTParam pptParam) {
        if (null == pptParam.getFilename() || "".equals(pptParam.getFilename())){
            return CommonResult.failed("未成功上传图片");
        }
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        CommodityParam commodityParam = new CommodityParam();
        commodityParam.setFilename(pptParam.getFilename());
        commodityParam.setSellerId(userId);
        commodityParam.setLessonId(pptParam.getLessonId());
        commodityParam.setSinglePrint(pptParam.getSinglePrint());
        commodityParam.setNewDegree(pptParam.getNewDegree());
        commodityParam.setPaperSize(pptParam.getPaperSize());
        commodityParam.setPrice(pptParam.getPrice());
        commodityParam.setContent(pptParam.getContent());
        commodityParam.setChapters(pptParam.getChapters());
        commodityParam.setType(1);
        commodityParam.setName(pptParam.getName());
        return CommonResult.success(commodityService.createCommodity(Long.parseLong(authenticationFacade.getAuthentication().getName()), 1, commodityParam));
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addBooks(@RequestBody BookParam bookParam) {
        if (null == bookParam.getFilename() || "".equals(bookParam.getFilename())){
            return CommonResult.failed("未成功上传图片");
        }
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        CommodityParam commodityParam = new CommodityParam();
        commodityParam.setFilename(bookParam.getFilename());
        commodityParam.setSellerId(userId);
        commodityParam.setLessonId(bookParam.getLessonId());
        commodityParam.setName(bookParam.getName());
        commodityParam.setAuthor(bookParam.getAuthor());
        commodityParam.setContent(bookParam.getContent());
        commodityParam.setPublisher(bookParam.getPublisher());
        commodityParam.setNewDegree(bookParam.getNewDegree());
        commodityParam.setPrice(bookParam.getPrice());
        commodityParam.setType(2);
        commodityParam.setName(bookParam.getName());
        return CommonResult.success(commodityService.createCommodity(Long.parseLong(authenticationFacade.getAuthentication().getName()), 2, commodityParam));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addNotes(@RequestBody NotesParam notesParam) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        if (null == notesParam.getFilename() || "".equals(notesParam.getFilename())){
            return CommonResult.failed("未成功上传图片");
        }
        CommodityParam commodityParam = new CommodityParam();
        commodityParam.setFilename(notesParam.getFilename());
        commodityParam.setSellerId(userId);
        commodityParam.setLessonId(notesParam.getLessonId());
        commodityParam.setPrice(notesParam.getPrice());
        commodityParam.setContent(notesParam.getContent());
        commodityParam.setCoverPercentage(notesParam.getCoverPercentage());
        commodityParam.setType(3);
        commodityParam.setName(notesParam.getName());
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
    public CommonResult<Object> getCommodities(@RequestParam(value = "isMine") Optional<Boolean> isMine,
                                               @RequestParam(value = "type") Optional<Integer> type,
                                               @RequestParam(value = "isSold") Optional<Boolean> isSold,
                                               @RequestParam(value = "lessonId") Optional<String> lessonId,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize) {
        Long userId = null;
        if (isMine.isPresent()) {
            userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        }
        return CommonResult.success(CommonPage.restPage(commodityService.getCommodities(userId, type.orElse(1), lessonId.orElse(null), isSold.orElse(false),
                isMine.orElse(false), pageNum, pageSize)));
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
    public CommonResult<Object> getFavorites(@RequestParam(value = "pageNum") Optional<Integer> pageNum,
                                             @RequestParam(value = "pageSize") Optional<Integer> pageSize) {
        Long userId = Long.parseLong(authenticationFacade.getAuthentication().getName());
        Integer num = pageNum.orElse(-1);
        Integer size = pageSize.orElse(-1);
        //不分页
        if (num == -1 && size == -1){
            return CommonResult.success(commodityService.listFavoriteCommodities(userId, -1, -1));
        }else{
            List<CommodityInfos> commodityInfos = commodityService.listFavoriteCommodities(userId, pageNum.orElse(-1), pageSize.orElse(-1));
            if (null == commodityInfos){
                return CommonResult.failed("该用户暂时没有收藏");
            }
            return CommonResult.success(CommonPage.restPage(commodityInfos));
        }
    }
}
