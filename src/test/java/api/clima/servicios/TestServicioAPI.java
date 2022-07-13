package api.clima.servicios;

import api.clima.modelo.Clima;
import api.clima.repositorios.RepositorioAPI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestServicioAPI {

    private ServicioAPI servicioAPI;
    private RepositorioAPI repositorioAPI;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        repositorioAPI = mock(RepositorioAPI.class);
        restTemplate = new RestTemplate();
        servicioAPI = new ServicioAPIImpl(restTemplate, repositorioAPI);
    }
}
