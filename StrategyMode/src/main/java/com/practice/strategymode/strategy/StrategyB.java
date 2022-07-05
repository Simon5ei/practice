package com.practice.strategymode.strategy;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class StrategyB implements ApiStrategy{
    public String Out(JSONObject data){
        System.out.println("Using Strategy B");
        try {
            System.out.println(data.getJSONObject("remark"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return data.toString();
    }

    public StrategyB() {
    }
}
