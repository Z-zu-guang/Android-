package com.sise.android.Api;

import com.sise.android.pojo.Customer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "test", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class testController {
    @RequestMapping("test")
    public String test(@RequestBody Customer customer) {
        System.out.println(customer);
        return "xixi";
    }
}
