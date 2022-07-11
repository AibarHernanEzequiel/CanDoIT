package api.clima.servicios;

import api.clima.modelo.Clima;
import api.clima.repositorios.RepositorioAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ServicioAPIImpl implements ServicioAPI {

    private RestTemplate restTemplate;
    private RepositorioAPI repositorioAPI;

    @Autowired
    public ServicioAPIImpl(RestTemplate restTemplate, RepositorioAPI repositorioAPI) {
        this.restTemplate = restTemplate;
        this.repositorioAPI = repositorioAPI;
    }

    @Override
    public List<Clima> obtenerListadoDeClimasDeLaAPI() {
        return Arrays.asList(restTemplate.getForObject("https://ws.smn.gob.ar/map_items/weather", Clima[].class));
    }

    @Override
    public void guardarListaObtenidaEnELRepositorio(List<Clima> climas) {
        repositorioAPI.guardarAPI(climas);
    }

    @Override
    public List<Clima> obtenerListadoDeClimasDelRepositorio() {
        List<Clima> climas = repositorioAPI.obtenerTodosLosClimas();
        if (climas.isEmpty()) {
            List<Clima> climasDeLaAPI = this.obtenerListadoDeClimasDeLaAPI();
            this.guardarListaObtenidaEnELRepositorio(climasDeLaAPI);
            return repositorioAPI.obtenerTodosLosClimas();
        }
        return climas;
    }

}
