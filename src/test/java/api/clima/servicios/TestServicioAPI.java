package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import api.clima.modelo.Clima;
import api.clima.repositorios.RepositorioAPI;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestServicioAPI {

    private ServicioAPI servicioAPI;
    private RepositorioAPI repositorioAPI;
    private RestTemplate restTemplate;
    private ServicioModelMapper servicioModelMapper;
    private ModelMapper modelMapper;

    @Before
    public void setup() {
        repositorioAPI = mock(RepositorioAPI.class);
        restTemplate = new RestTemplate();
        modelMapper = new ModelMapper();
        servicioModelMapper = new ServicioModelMapperImpl(modelMapper);
        servicioAPI = new ServicioAPIImpl(this.restTemplate, this.repositorioAPI, servicioModelMapper);
    }

    @Test
    public void cuando_obtengo_climas_del_repo_deberia_devolver_una_lista_de_ClimasDTO() {
        givenQueEnElRepositorioHayUnaListaDeClimas();
        List<ClimaDTO> climaDTOList = whenObtengoLosClimasDelRepositorio();
        thenDeberiaObtenerUnaListaDeClimasDTO(climaDTOList);
        andVerificarQueSeLlamoAlMetodoDelRepositorio();
    }

    private void givenQueEnElRepositorioHayUnaListaDeClimas() {
        List<Clima> climaList = agregarClimasALaLista();
        when(this.repositorioAPI.obtenerTodosLosClimas()).thenReturn(climaList);
    }

    private List<Clima> agregarClimasALaLista() {
        List<Clima> climaList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            climaList.add(new Clima());
        }
        return climaList;
    }

    private List<ClimaDTO> whenObtengoLosClimasDelRepositorio() {
        return this.servicioAPI.obtenerListaDeClimasDTO();
    }

    private void thenDeberiaObtenerUnaListaDeClimasDTO(List<ClimaDTO> climaDTOList) {
        assertThat(climaDTOList.stream().iterator().next()).isInstanceOf(ClimaDTO.class);
    }

    private void andVerificarQueSeLlamoAlMetodoDelRepositorio() {
        verify(repositorioAPI, times(1)).obtenerTodosLosClimas();
    }
}
