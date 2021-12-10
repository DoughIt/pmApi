package com.pm.pmapi.controller;

import com.pm.pmapi.common.api.CommonPage;
import com.pm.pmapi.common.api.CommonResult;
import com.pm.pmapi.component.IAuthenticationFacade;
import com.pm.pmapi.dto.MessageParam;
import com.pm.pmapi.dto.MessageSummaryInfo;
import com.pm.pmapi.mbg.model.TabMessage;
import com.pm.pmapi.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Jerry Zhang <https://github.com/doughit>
 * @Description 消息管理Controller
 * @Copyright DoughIt Studio - Powered By DoughIt
 * @date 2021-12-07 11:50
 */
@Controller
@RequestMapping("/api/mms")
public class MmsController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    /**
     * 获取我的消息列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/msgList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getMyMessages(@RequestParam("userId") Optional<Long> userId,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize) {

        if (authenticationFacade.getAuthentication() == null) {
            return CommonResult.unauthorized(null);
        }
        Long mine = Long.parseLong(authenticationFacade.getAuthentication().getName());
        if (userId.isPresent()) {
            List<TabMessage> messageList = messageService.listMessagesByTwo(userId.get(), mine, pageNum, pageSize);
            messageList.forEach(message -> {
                // 将mine请求的消息设置为已读
                if (!message.getReadStatus() && message.getReceiverId().equals(mine)) {
                    message.setReadStatus(true);
                    messageService.updateReadStatus(message.getId(), true);
                }
            });
            return CommonResult.success(CommonPage.restPage(messageList));
        } else {
            List<MessageSummaryInfo> messageSummaryInfoList = messageService.listMessageSummariesByUserId(mine, pageNum, pageSize);
            return CommonResult.success(CommonPage.restPage(messageSummaryInfoList));
        }
    }

    /**
     * 发送消息
     *
     * @param messageParam
     * @return
     */
    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sendMessage(@RequestBody MessageParam messageParam) {
        TabMessage message = new TabMessage();
        BeanUtils.copyProperties(messageParam, message);
        message.setSenderId(Long.valueOf(authenticationFacade.getAuthentication().getName()));
        int status = messageService.createMessage(message);
        if (status <= 0) {
            return CommonResult.failed("发送失败");
        }
        return CommonResult.success(null);
    }
}
