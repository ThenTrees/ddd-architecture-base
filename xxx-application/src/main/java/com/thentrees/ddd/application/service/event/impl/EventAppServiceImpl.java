package com.thentrees.ddd.application.service.event.impl;

import com.thentrees.ddd.application.service.event.EventAppService;
import org.springframework.stereotype.Service;

@Service
public class EventAppServiceImpl implements EventAppService {
    @Override
    public String sayHello(String who) {
        return "Hello Application";
    }
}
