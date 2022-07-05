package com.practice.strategymode.strategy;

import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface ApiStrategy {
    public String Out(JSONObject data);
}
