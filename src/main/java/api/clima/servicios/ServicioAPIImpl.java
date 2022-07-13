package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import api.clima.modelo.Clima;
import api.clima.repositorios.RepositorioAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@EnableScheduling
public class ServicioAPIImpl implements ServicioAPI {

    private RestTemplate restTemplate;
    private RepositorioAPI repositorioAPI;

    @Autowired
    public ServicioAPIImpl(RestTemplate restTemplate, RepositorioAPI repositorioAPI) {
        this.restTemplate = restTemplate;
        this.repositorioAPI = repositorioAPI;
    }


    @Override
    public List<ClimaDTO> obtenerListaDeClimas() {
        return null;
    }
}
