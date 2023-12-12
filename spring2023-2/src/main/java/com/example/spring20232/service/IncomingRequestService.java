package com.example.spring20232.service;

import com.example.spring20232.binding.viewDTO.IncomingRequestPathsCountViewDto;

import java.util.Map;

public interface IncomingRequestService {


    boolean countRequestedPaths(String requestedPath);

    IncomingRequestPathsCountViewDto alarmWhenCountExceeded(Map<String, Integer> filteredMap);

    void clearMap();


}
