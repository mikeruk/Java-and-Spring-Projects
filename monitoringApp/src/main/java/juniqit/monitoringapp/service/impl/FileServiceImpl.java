package juniqit.monitoringapp.service.impl;

import juniqit.monitoringapp.config.GlobalConstants;
import juniqit.monitoringapp.service.FileService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public List<String> readServerURLs() throws IOException {
        return Files.lines(Path.of(GlobalConstants.SERVERS_URL_FILE_PATH))
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public URL parseSingleURL(String url) throws MalformedURLException {
        return new URL(url);
    }


    @Override
    public List<URL> parseAllURLs(List<String> urlList) throws MalformedURLException {

        return urlList.stream().map(url -> {
            try {
                return parseSingleURL(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
