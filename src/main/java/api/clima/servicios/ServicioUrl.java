package api.clima.servicios;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServicioUrl {

    public static String obtenerUrlAPIDeArchivoProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("./urlAPI.properties"));
            return properties.getProperty("url_API");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
