package juniqit.monitoringapp.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface FileService {

    List<String> readServerURLs() throws IOException;

    URL parseSingleURL(String url) throws MalformedURLException;

    List<URL> parseAllURLs(List<String> urlList) throws MalformedURLException;

}
