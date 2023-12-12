package juniqit.monitoringapp.service.impl;

import juniqit.monitoringapp.service.LogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LogServiceImpl implements LogService  {


    @Override
    public LocalDateTime createTimeStamp() {
        return LocalDateTime
                .parse(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss")
                        .format(LocalDateTime.now()), DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss"));
    }
}
