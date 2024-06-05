package com.sise.android.Api;

import com.google.gson.Gson;
import com.sise.android.pojo.*;
import com.sise.android.service.InformationService;
import com.sise.android.service.UserService;
import com.sise.android.util.ReturnJsonMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author draven
 */

@RestController
@RequestMapping(value = "information", produces = {"text/html;charset=UTF-8;", "application/json;charset=UTF-8;"})
public class InformationApi {
    @Resource
    InformationService informationService;
    @Resource
    UserService userService;

    @PostMapping("findCourse")
    public String findCourseBySid(@RequestBody Map<String, String> map) {
        return ReturnJsonMessage.returnMessage(informationService.findCourseBySid(map.get("id")));
    }

    @PostMapping("score")
    public String findScoreBySid(@RequestBody Map<String, String> map) {
        List<Result> scoreBySno = informationService.findScoreBySno(map.get("no"));
        for (Result result : scoreBySno) {
            if (result.getId() == null) {
                Results results = informationService.addScoreBySidAndCid(Results.builder().sid(map.get("no")).cid(result.getCid()).build());
                result.setId(results.getId());
            }
        }
        return ReturnJsonMessage.returnMessage(scoreBySno);
    }

    @PostMapping("findStudent")
    public String findStudentInfoBySno(@RequestBody Map<String, String> map) {
        Customer customer = userService.findStudentInfoBySno(map.get("no"));
        List<Classed> allClass = informationService.findAllClass();
        if (customer != null && allClass != null) {
            for (Classed aClass : allClass) {
                if (customer.getClassId().equals(aClass.getId())) {
                    customer.setClassed(aClass);
                    break;
                }
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("customer", customer);
            map1.put("class", allClass);
            return ReturnJsonMessage.returnMessage(map1);
        }
        return ReturnJsonMessage.DIYMessage(new Message("500", "学号不存在"));

    }

    @PostMapping("addScoreBySidAndCid")
    public String addScoreBySidAndCid(@RequestBody Map<String, String> map) {
        Results results = Results.builder().sid(map.get("sid")).cid(map.get("cid")).build();
        results = informationService.addScoreBySidAndCid(results);
        return ReturnJsonMessage.returnMessage(results.getId());
    }

    @PostMapping("updateScore")
    public String updateScoreByRid(@RequestBody Map<String, Object> map) {
        List list = (List) map.get("list");
        List<Result> results = new ArrayList<>();
        int i = 0;
        for (Object o : list) {
            Result result = new Gson().fromJson(new Gson().toJson(o), Result.class);
            results.add(result);
        }
        for (Result result : results) {
            i += informationService.updateScoreByRid(result.getId(), result.getScore());
        }
        return ReturnJsonMessage.returnMessage(i);
    }
}
