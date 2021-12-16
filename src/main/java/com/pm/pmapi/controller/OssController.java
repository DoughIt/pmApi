package com.pm.pmapi.controller;

import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.dto.OssCallbackResult;
import com.pm.pmapi.dto.OssPolicyResult;
import com.pm.pmapi.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description Oss上传操作接口控制
 *
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @author Jerry Zhang <https://github.com/doughit>
 * @date 2021-12-16 12:49
 */
@Controller
@RequestMapping("/api/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    /**
     * oss上传签名生成
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    public CommonResult<OssPolicyResult> policy() {
        return CommonResult.success(ossService.policy());
    }

    /**
     * oss上传成功回调
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        return CommonResult.success(ossService.callback(request));
    }
}
