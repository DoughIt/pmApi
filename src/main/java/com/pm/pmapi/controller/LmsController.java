package com.pm.pmapi.controller;


import com.pm.pmapi.common.api.CommonPage;
import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.service.LessonService;
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
        return CommonResult.success(lessonService.getLessonByLessonId(userId, lessonId));
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
            @RequestParam(value = "keys", defaultValue = "") Option<String> keys) {
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
                lessonList.addAll(lessonService.listLessonsByTypeAndKey(userId, 2, keys.getValue(), pageNum, pageSize));
                break;
            default:
                CommonResult.failed("查询失败");
        }
        return CommonResult.success(CommonPage.restPage(lessonList));
    }

    

}
