package com.sunshine.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @RequestMapping("/getUserInfo")
    @SentinelResource(value="user-info")
    public Map<String, Object> getUserInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("userName", "王家凯");
        map.put("age", 31);
        map.put("sex", 1);
        return map;
    }

    @RequestMapping("/getUserMessage")
    @SentinelResource(value="user-info")
    public Map<String, Object> getUserMessage() {

        TraceContext.putCorrelation("myKey", "myValue");
        Optional<String> op = TraceContext.getCorrelation("myKey");
        log.info("myValue = {} ", op.get());

        String traceId = TraceContext.traceId();
        log.info("traceId = {} ", traceId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("userName", "王家凯");
        map.put("age", 31);
        map.put("sex", 1);
        return map;
    }

    @RequestMapping("/getUserOrder")
    @SentinelResource(value = "order")
    public Map<String, Object> getUserOrder(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("orderMessage", "王家凯的订单");
        map.put("time", new Date().getTime());
        return map;
    }
}
