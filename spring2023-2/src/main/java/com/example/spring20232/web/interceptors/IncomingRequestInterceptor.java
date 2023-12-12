package com.example.spring20232.web.interceptors;

import com.example.spring20232.binding.viewDTO.IncomingRequestPathsCountViewDto;
import com.example.spring20232.service.IncomingRequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Locale;
import java.util.Map;

@Component
public class IncomingRequestInterceptor implements HandlerInterceptor {

    private final IncomingRequestService incomingRequestService;

    private ThymeleafViewResolver tlvr;

    public IncomingRequestInterceptor(IncomingRequestService incomingRequestService, ThymeleafViewResolver tlvr) {
        this.incomingRequestService = incomingRequestService;
        this.tlvr = tlvr;
    }

    private String previousSessionID = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        System.out.println("ACCEPT: " + request.getHeader("Accept"));
        System.out.println("Accept-Encoding: " + request.getHeader("Accept-Encoding"));
        System.out.println("Accept-Language: " + request.getHeader("Accept-Language"));
        System.out.println("Connection: " + request.getHeader("Connection"));
        System.out.println("Cookie: " + request.getHeader("Cookie"));
        System.out.println("Host: " + request.getHeader("Host"));
        System.out.println("Sec-Fetch-Dest: " + request.getHeader("Sec-Fetch-Dest"));
        System.out.println("Sec-Fetch-Mode: " + request.getHeader("Sec-Fetch-Mode"));
        System.out.println("User-Agent: " + request.getHeader("User-Agent"));
        System.out.println("sec-ch-ua: " + request.getHeader("sec-ch-ua"));
        System.out.println("sec-ch-ua-mobile: " + request.getHeader("sec-ch-ua-mobile"));
        System.out.println("sec-ch-ua-platform: " + request.getHeader("sec-ch-ua-platform"));




        String requestedPath = getRequestedPath(request);
        boolean counted = incomingRequestService.countRequestedPaths(requestedPath);


        if(this.previousSessionID != null) {


            if (!this.previousSessionID.equals(request.getRequestedSessionId())) {
                this.previousSessionID = request.getRequestedSessionId();
                incomingRequestService.clearMap();
            }
        }
        if (counted) {
            View countedView = tlvr.resolveViewName("count-pages", Locale.getDefault());
            if (countedView != null) {
                countedView.render(Map.of(), request, response);
            }
            return false;
        }

        return true;

    }

    private String getRequestedPath(HttpServletRequest request) {
        String requestedServletPath = request.getServletPath();
        return requestedServletPath;
    }

}
