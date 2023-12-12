package com.example.spring20232.service.impl;

import com.example.spring20232.binding.viewDTO.IncomingRequestPathsCountViewDto;
import com.example.spring20232.service.IncomingRequestService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class IncomingRequestServiceImpl implements IncomingRequestService {

    private Map<String, Integer> count;

    IncomingRequestPathsCountViewDto incomingRequestPathsCountViewDto = new IncomingRequestPathsCountViewDto();


    public IncomingRequestServiceImpl() {
        this.count = new HashMap<>();
    }



    @Override
    public boolean countRequestedPaths(String requestedPath) {


        switch (requestedPath) {
            case "/work-experience":
                if (count.containsKey("/work-experience")) {
                    count.put("/work-experience", count.get("/work-experience") + 1);
                } else {
                    count.put(requestedPath, 1);
                }
                ;
                break;
            case "/skills":
                if (count.containsKey("/skills")) {
                    count.put("/skills", count.get("/skills") + 1);
                } else {
                    count.put(requestedPath, 1);
                }
                ;
                break;
            case "/education":
                if (count.containsKey("/education")) {
                    count.put("/education", count.get("/education") + 1);
                } else {
                    count.put(requestedPath, 1);
                }
                ;
                break;

            case "/certificates":
                if (count.containsKey("/certificates")) {
                    count.put("/certificates", count.get("/certificates") + 1);
                } else {
                    count.put(requestedPath, 1);
                }
                ;
                break;

            default:
                break;
        }
        System.out.println();
        count.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "  " + e.getValue()));

        Map<String, Integer> counted = count.entrySet().stream()
                .filter(e -> e.getValue() > 3)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        if(!counted.isEmpty()){
            //count.clear();
            clearMap();
            return true;
        }
        return false;




    }


    @Override
    public IncomingRequestPathsCountViewDto alarmWhenCountExceeded(Map<String, Integer> filteredMap) {
        System.out.println();
        this.incomingRequestPathsCountViewDto.setCount(filteredMap);
        return this.incomingRequestPathsCountViewDto;
    }

    public void clearMap(){
        this.count.clear();
    }


}
