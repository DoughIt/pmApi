package com.pm.pmapi.controller;


import com.pm.pmapi.common.api.CommonPage;
import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.dto.TagEvaluateParam;
import com.pm.pmapi.dto.TagParam;
import com.pm.pmapi.service.LessonService;
import com.pm.pmapi.service.TagService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.pm.pmapi.component.IAuthenticationFacade;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 课程模块控制器，负责处理课程信息、课程标签信息、课程帖子信息等
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-11-16 17:16
 */
@Controller
@RequestMapping("/api/lms")
public class LmsController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private TagService tagService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    /**
     * 获取课程信息
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getInfo(@RequestParam(value = "lessonId") Long lessonId) {
        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        }

        if(null != lessonService.getLessonByLessonId(userId, lessonId)) {
            return CommonResult.success(lessonService.getLessonByLessonId(userId, lessonId));
        } else {
            return CommonResult.failed("课程不存在");
        }
    }


    /**
     * 获取课程列表
     */
    @RequestMapping(value = "/lessonList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult listLessons(
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @NotEmpty @RequestParam(value = "filter") String filter,
            @RequestParam(value = "keys", defaultValue = "") Optional<String> keys) {
        ArrayList<Object> lessonList = new ArrayList<>();

        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        }

        switch (filter) {
            case "hot":
                lessonList.addAll(lessonService.listLessonsByType(userId, 1, pageNum, pageSize));
                break;
            case "query":
                lessonList.addAll(lessonService.listLessonsByTypeAndKey(userId, 2, keys.orElse(""), pageNum, pageSize));
                break;
            default:
                return CommonResult.failed("查询失败");
        }
        return CommonResult.success(CommonPage.restPage(lessonList));
    }

    /**
     * 获取课程标签列表
     */
    @RequestMapping(value = "/tagList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult listLessons(
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "lessonId") Long lessonId) {
        ArrayList<Object> tagList = new ArrayList<>();

        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        }

        tagList.addAll(tagService.listTagsOfLessonByLessonId(userId, lessonId, pageNum, pageSize));
        return CommonResult.success(CommonPage.restPage(tagList));
    }

    /**
     * 添加课程标签
     */
    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addLessonTag(@RequestBody TagParam tagParam) {
        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        }

        Integer result = tagService.createTag(userId, tagParam.getLessonNumber(), tagParam.getTagName(), tagParam.getIsPositive());

        if(result == 0) {
            return CommonResult.failed("创建失败！该标签已存在");
        } else if(result == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("创建失败！");
        }
    }

    /**
     * 获取课程标签
     */
    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getLessonTag(@RequestParam(value = "lessonId") Long lessonId, @RequestParam(value = "tagName") String tagName) {
        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        }
        if(null != tagService.getTagByLessonIdAndTagName(userId, lessonId, tagName)) {
            return CommonResult.success(tagService.getTagByLessonIdAndTagName(userId, lessonId, tagName));
        } else {
            return CommonResult.failed("课程标签不存在");
        }

    }

    /**
     * 评价课程标签
     */
    @RequestMapping(value = "/tag", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult evaluateLessonTag(@RequestBody TagEvaluateParam tagEvaluateParam) {
        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        }

        Boolean result = tagService.evaluateTag(userId, tagEvaluateParam.getLessonId(), tagEvaluateParam.getTagName(),
                tagEvaluateParam.getEvaluate());

        if(result) {
            return CommonResult.success("评价成功");
        } else {
            return CommonResult.failed("评价失败");
        }
    }

    /**
     * 添加收藏课程
     */
    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> addFavorite(@RequestParam(value = "id") Long lessonId) {
        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        } else {
            return CommonResult.failed("添加失败");
        }
        Boolean result = lessonService.addFavoriteLesson(userId, lessonId);

        if(result) {
            return CommonResult.success("添加成功");
        } else {
            return CommonResult.failed("添加失败");
        }
    }

    /**
     * 删除收藏课程
     */
    @RequestMapping(value = "/favorite", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult<Object> deleteFavorite(@RequestParam(value = "id") Long lessonId) {
        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        } else {
            return CommonResult.failed("删除失败");
        }
        Boolean result = lessonService.deleteFavoriteLesson(userId, lessonId);

        if(result) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }

    /**
     * 获取收藏课程列表
     */
    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getFavorites() {
        Optional<Long> userId = Optional.empty();
        if (authenticationFacade.getAuthentication() != null) {
            userId = Optional.of(Long.parseLong(authenticationFacade.getAuthentication().getName()));
        } else {
            return CommonResult.failed("获取失败");
        }
        return CommonResult.success(lessonService.listFavoriteLessons(userId));
    }

}
