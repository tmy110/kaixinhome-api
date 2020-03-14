package com.kaixin8848.home.web.qiyuesuo.controller;


import com.google.gson.Gson;
import com.kaixin8848.home.utility.Log4J2.LogHelper;
import com.kaixin8848.home.web.qiyuesuo.pojo.in.QiyuesuoMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by tmy on 2019/12/08.
 */
@RestController
@RequestMapping("/qiyuesuo")
@Api(description = "契约锁操作接口")
public class QiyuesuoController {
    //http://kaixin8848.com/kaixinhome-api/qiyuesuo/receive-qiyuesuo-notification
    @ApiOperation(value = "接收契约锁通知接口", notes = "新增用户")
    @PostMapping("/receive-qiyuesuo-notification")
    public String receiveQiyuesuoNotification(@RequestParam Map<String, String> params) {
        String data = params.get("content");
        LogHelper.info("契约锁操作接口收到的数据:" + data, "QiyuesuoController", "receiveQiyuesuoNotification");
        return "success";
    }


}
