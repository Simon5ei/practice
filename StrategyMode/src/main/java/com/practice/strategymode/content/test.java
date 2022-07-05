package com.practice.strategymode.content;

import com.practice.strategymode.build.strategyBuilder;
import com.practice.strategymode.strategy.ApiStrategy;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {
    @PostMapping("/commonData")
    public @ResponseBody Object commonData(@RequestBody String Json) throws JSONException {
        JSONObject object = new JSONObject(Json);
        ApiStrategy com= strategyBuilder.getStrategyMap(object.getString("busiCode"));
        String msg=com.Out(object);
        return ("查询成功:"+msg);
    }
}

//{
//    "busiCode":"1002",
//    "remark":{"mark":"I am api 2","param1":"I have a lot of params","data":"2"}
//}

//{
//    "busiCode":"1001",
//    "remark":"I am api 1"
//}

