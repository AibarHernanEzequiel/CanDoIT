package api.clima.servicios;

import org.springframework.beans.factory.annotation.Value;

public class UrlAPI {

    private String url_api;


    public UrlAPI() {
    }

    public String getUrl_api() {
        return url_api;
    }

    public void setUrl(String url_api) {
        this.url_api = url_api;
    }
}
