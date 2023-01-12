package com.hexagon.framework.adapters.input.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @GetMapping("/test/{id}")
    public Object test(@PathVariable UUID id) {
        return id != null ? id : UUID.randomUUID();
    }
}
