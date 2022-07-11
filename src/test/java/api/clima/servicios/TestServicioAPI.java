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

    @Test
    public void cunadoObtengoDelRepoUnaListaVaciaDeberiaConsumirLaAPIYPersistirla() {
        givenQueObtengoDelRepositorioUnaListaVacia();
        whenSolicitoAlRepositorioUnaListaConClimas();
        List<Clima> climasDeLaAPI = thenDeberiaConsumirLaAPIYGuardarlaEnElRepositorio();
        andObtenerDeNuevoLaListaDelRepositorio(climasDeLaAPI);
    }

    @Test
    public void cunadoSolicitoAlRepositorioUnaListaDeClimasDeberiaDevolverUnaListaConClimas() {
        givenQueElRepositorioTieneUnaListaConClimas();
        List<Clima> obtenidaDelRepo = whenSolicitoAlRepositorioUnaListaConClimas();
        thenDeberiaNoEstarVacia(obtenidaDelRepo);
    }

    @Test
    public void alConsumirUnaAPICuandoLaPersistoAlObtenerlaNoDeberiaEstarVacia() {
        List<Clima> climasDeLaAPI = givenQueConsumoUnaAPIDeClimas("https://ws.smn.gob.ar/map_items/weather");
        whenGuardoLaListaEnElRepositorio(climasDeLaAPI);
        thenCuandoLaObtengoNoDeberiaEstarVacia();
    }

    private void givenQueElRepositorioTieneUnaListaConClimas() {
        List<Clima> climaList = servicioAPI.obtenerListadoDeClimasDeLaAPI();
        when(repositorioAPI.obtenerTodosLosClimas()).thenReturn(climaList);
    }

    private void givenQueObtengoDelRepositorioUnaListaVacia() {
        when(repositorioAPI.obtenerTodosLosClimas()).thenReturn(new ArrayList<>());
    }

    private List<Clima> givenQueConsumoUnaAPIDeClimas(String url) {
        return Arrays.asList(restTemplate.getForObject(url, Clima[].class));
    }

    private List<Clima> whenSolicitoAlRepositorioUnaListaConClimas() {
        return servicioAPI.obtenerListadoDeClimasDelRepositorio();
    }

    private void whenGuardoLaListaEnElRepositorio(List<Clima> climasDeLaAPI) {
        servicioAPI.guardarListaObtenidaEnELRepositorio(climasDeLaAPI);
        when(repositorioAPI.obtenerTodosLosClimas()).thenReturn(climasDeLaAPI);
    }

    private List<Clima> thenDeberiaConsumirLaAPIYGuardarlaEnElRepositorio() {
        List<Clima> climasDeLaAPI = servicioAPI.obtenerListadoDeClimasDeLaAPI();
        servicioAPI.guardarListaObtenidaEnELRepositorio(climasDeLaAPI);
        when(servicioAPI.obtenerListadoDeClimasDelRepositorio()).thenReturn(climasDeLaAPI);
        return climasDeLaAPI;
    }

    private void thenDeberiaNoEstarVacia(List<Clima> obtenidaDelRepo) {
        assertThat(obtenidaDelRepo).isNotEmpty();
    }

    private void andObtenerDeNuevoLaListaDelRepositorio(List<Clima> climasDeLaAPI) {
        assertThat(servicioAPI.obtenerListadoDeClimasDelRepositorio()).isNotEmpty();
        assertThat(servicioAPI.obtenerListadoDeClimasDelRepositorio()).isEqualTo(climasDeLaAPI);
    }

    private void thenCuandoLaObtengoNoDeberiaEstarVacia() {
        assertThat(servicioAPI.obtenerListadoDeClimasDelRepositorio()).isNotEmpty();
    }
}
