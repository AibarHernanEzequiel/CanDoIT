package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import api.clima.modelo.Clima;
import api.clima.repositorios.RepositorioAPI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestServicioAPI {

    private ServicioAPI servicioAPI;
    private RepositorioAPI repositorioAPI;

    @Before
    public void setup() {
        repositorioAPI = mock(RepositorioAPI.class);
        RestTemplate restTemplate = new RestTemplate();
        servicioAPI = new ServicioAPIImpl(restTemplate, this.repositorioAPI);
    }

    @Test
    public void cuando_obtengo_climas_del_repo_deberia_devolver_una_lista_de_ClimasDTO() {
        givenQueEnElRepositorioHayUnaListaDeClimas();
        List<ClimaDTO> climaDTOList = whenSolicitoAlRepositorioUnaListaDeClimas();
        thenDeberiaObtenerUnaListaDeClimasDTO(climaDTOList);
        andVerificarQueSeLlamoAlMetodoDelRepositorio();
    }

    @Test
    public void cuando_el_repo_devuelve_una_lista_vacia_deberia_consumir_y_persistir_la_API() {
        givenQueElRepositorioDevuelveUnaListaVacia();
        whenSolicitoAlRepositorioUnaListaDeClimas();
        thenDeberiaConsumirLaAPIyPersistirla();
        andVerificarQueSeLlameAlMetodoDelRepositorio();
    }

    private void givenQueElRepositorioDevuelveUnaListaVacia() {
        when(repositorioAPI.obtenerTodosLosClimas()).thenReturn(null);
    }

    private void givenQueEnElRepositorioHayUnaListaDeClimas() {
        List<Clima> climaList = agregarClimasALaLista();
        when(this.repositorioAPI.obtenerTodosLosClimas()).thenReturn(climaList);
    }

    private List<ClimaDTO> whenSolicitoAlRepositorioUnaListaDeClimas() {
        return this.servicioAPI.obtenerListaDeClimasDTO();
    }

    private void thenDeberiaObtenerUnaListaDeClimasDTO(List<ClimaDTO> climaDTOList) {
        assertThat(climaDTOList.stream().iterator().next()).isInstanceOf(ClimaDTO.class);
    }

    private void andVerificarQueSeLlamoAlMetodoDelRepositorio() {
        verify(repositorioAPI, times(1)).obtenerTodosLosClimas();
    }

    private List<Clima> agregarClimasALaLista() {
        List<Clima> climaList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            climaList.add(new Clima());
        }
        return climaList;
    }

    private void thenDeberiaConsumirLaAPIyPersistirla() {
        List<ClimaDTO> climaDTOList = servicioAPI.consumirAPI();
        servicioAPI.guardarDatosObtenidosDeLaAPI(climaDTOList);
    }

    private void andVerificarQueSeLlameAlMetodoDelRepositorio() {
        verify(repositorioAPI, times(2)).persistirDatosDeLaAPI(any());
    }
}
