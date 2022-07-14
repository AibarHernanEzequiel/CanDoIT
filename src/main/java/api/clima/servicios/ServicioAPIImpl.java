package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import api.clima.modelo.Clima;
import api.clima.repositorios.RepositorioAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
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
    private final String URL = "https://ws.smn.gob.ar/map_items/weather";
    private ServicioModelMapper servicioModelMapper = new ServicioModelMapper();

    @Autowired
    public ServicioAPIImpl(RestTemplate restTemplate, RepositorioAPI repositorioAPI) {
        this.restTemplate = restTemplate;
        this.repositorioAPI = repositorioAPI;
    }

    @Override
    public List<ClimaDTO> consumirAPI() {
        return Arrays.asList(restTemplate.getForObject(URL, ClimaDTO[].class));
    }

    @Override
    public void guardarDatosObtenidosDeLaAPI(List<ClimaDTO> climaDTOList) {
        List<Clima> climasDeLaAPI = servicioModelMapper.mapearDTOAObjetoDeDominio(climaDTOList);
        repositorioAPI.persistirDatosDeLaAPI(climasDeLaAPI);
    }

    @Override
    public List<ClimaDTO> obtenerListaDeClimasDTO() {
        List<Clima> climas = this.obtenerClimasDelRepositorio();
        return servicioModelMapper.mapearObejetoDeDominioADTO(climas);
    }

    @Override
    public void actualizarRepositorio(List<ClimaDTO> climaDTOList) {
        List<Clima> climaList = servicioModelMapper.mapearDTOAObjetoDeDominio(climaDTOList);
        this.repositorioAPI.actualizarRepositorio(climaList);
    }

    private List<Clima> obtenerClimasDelRepositorio() {
        List<Clima> climas = this.repositorioAPI.obtenerTodosLosClimas();
        if (climas.isEmpty()) {
            this.repositorioAPI.persistirDatosDeLaAPI(climas);
            return this.repositorioAPI.obtenerTodosLosClimas();
        }
        return climas;
    }
}
