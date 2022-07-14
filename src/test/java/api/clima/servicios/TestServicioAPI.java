package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import api.clima.modelo.Clima;
import api.clima.repositorios.RepositorioAPI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestServicioAPI {

    private ServicioAPI servicioAPI;
    private RestTemplate restTemplate;
    private RepositorioAPI repositorioAPI;

    @Before
    public void setup() {
        repositorioAPI = mock(RepositorioAPI.class);
        restTemplate = new RestTemplate();
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

    @Test
    public void cuando_actualizo_el_repo_deberia_llamar_al_metodo_actualizar_y_la_lista_no_deberia_estar_vacia() {
        List<ClimaDTO> actualizada = givenQueReciboDatosAcutalizadosDeUnaAPi();
        whenActualizoElRepositorio(actualizada);
        whenObtengoTodosLosClimas();
        thenLaListaNoDeberiaEstarVacia();
        andThenDeberiaVerificarQueLlameAlMetodoDelRepositorio();

    }

    private void whenObtengoTodosLosClimas() {
        List<Clima> climas = Arrays.asList(restTemplate.getForObject("https://ws.smn.gob.ar/map_items/weather", Clima[].class));
        when(repositorioAPI.obtenerTodosLosClimas()).thenReturn(climas);
    }

    private void givenQueEnElRepositorioHayUnaListaDeClimas() {
        List<Clima> climaList = agregarClimasALaLista();
        when(this.repositorioAPI.obtenerTodosLosClimas()).thenReturn(climaList);
    }

    private void givenQueElRepositorioDevuelveUnaListaVacia() {
        when(repositorioAPI.obtenerTodosLosClimas()).thenReturn(new ArrayList<>());
    }

    private List<ClimaDTO> givenQueReciboDatosAcutalizadosDeUnaAPi() {
        return servicioAPI.consumirAPI();
    }

    private List<ClimaDTO> whenSolicitoAlRepositorioUnaListaDeClimas() {
        return this.servicioAPI.obtenerListaDeClimasDTO();
    }

    private void whenActualizoElRepositorio(List<ClimaDTO> actualizada) {
        servicioAPI.actualizarRepositorio(actualizada);
    }

    private List<Clima> agregarClimasALaLista() {
        List<Clima> climaList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            climaList.add(new Clima());
        }
        return climaList;
    }

    private void thenDeberiaObtenerUnaListaDeClimasDTO(List<ClimaDTO> climaDTOList) {
        assertThat(climaDTOList.stream().iterator().next()).isInstanceOf(ClimaDTO.class);
    }

    private void thenDeberiaConsumirLaAPIyPersistirla() {
        List<ClimaDTO> climaDTOList = servicioAPI.consumirAPI();
        servicioAPI.guardarDatosObtenidosDeLaAPI(climaDTOList);
    }

    private void andVerificarQueSeLlamoAlMetodoDelRepositorio() {
        verify(repositorioAPI, times(1)).obtenerTodosLosClimas();
    }

    private void andVerificarQueSeLlameAlMetodoDelRepositorio() {
        verify(repositorioAPI, times(2)).persistirDatosDeLaAPI(any());
    }

    private void andThenDeberiaVerificarQueLlameAlMetodoDelRepositorio() {
        verify(repositorioAPI, times(1)).actualizarRepositorio(any());
    }

    private void thenLaListaNoDeberiaEstarVacia() {
        assertThat(servicioAPI.obtenerListaDeClimasDTO()).isNotEmpty();
    }
}
