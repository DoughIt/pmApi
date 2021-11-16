package com.pm.pmapi.controller;


import com.pm.pmapi.common.api.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 课程模块控制器，负责处理课程信息、课程标签信息、课程帖子信息等
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-11-16 17:16
 */
@Controller
@RequestMapping("/api/lms")
public class LmsController {


    /**
     * 获取课程信息
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> getInfo(@RequestParam(value = "lessonId") String lessonId) {
        // TODO 创建课程dto作为返回类型
        return CommonResult.validateFailed("课程编号不存在！");
    }


    /**
     * 获取课程列表
     */
    @RequestMapping(value = "/lessonList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ArrayList<Object>> listLessons(
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @NotEmpty @RequestParam(value = "filter") String filter,
            @RequestParam(value = "keys", defaultValue = "") String keys) {
        ArrayList<Object> lessonList = new ArrayList<>();
        // TODO 创建课程dto作为返回类型
        switch (filter) {
            case "hot":
                // TODO 通过课程service获取热门课程相关数据
                lessonList.addAll(null);
                break;
            case "query":
                // TODO 通过课程service筛选课程相关数据
                lessonList.addAll(null);
                break;
            default:
                CommonResult.failed("查询失败");
        }
        return CommonResult.success(lessonList);
    }

}
