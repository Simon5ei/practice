package com.practice.strategymode.strategy;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class StrategyA implements ApiStrategy{
    public String Out(JSONObject data){
        System.out.println("Using Strategy A");
        try {
            System.out.println(data.getString("remark"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return data.toString();
    }

    public StrategyA() {
    }
}
