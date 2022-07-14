package api.clima.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:urlAPI.properties")
public class ServicioUrl {

    @Autowired
    private Environment environment;

    @Bean
    public UrlAPI urlAPI() {
        Properties properties = new Properties();
        UrlAPI urlAPI = new UrlAPI();
        try {
            properties.load(new FileReader("/home/ezequiel/Proyects/Java/CanDoIT/src/main/resources/urlAPI.properties"));
            urlAPI.setUrl(properties.getProperty("urlAPI"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return urlAPI;
    }
}
