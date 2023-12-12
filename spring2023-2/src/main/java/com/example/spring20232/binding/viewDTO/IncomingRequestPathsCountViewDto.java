package com.example.spring20232.binding.viewDTO;

import java.util.Map;

public class IncomingRequestPathsCountViewDto {

    private Map<String, Integer> count;

    public IncomingRequestPathsCountViewDto() {
    }

    public Map<String, Integer> getCount() {
        return count;
    }

    public void setCount(Map<String, Integer> count) {
        this.count = count;
    }
}
