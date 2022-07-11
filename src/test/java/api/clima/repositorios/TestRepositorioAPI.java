package api.clima.repositorios;

import api.clima.SpringTest;
import api.clima.modelo.Clima;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TestRepositorioAPI extends SpringTest {


    @Test
    @Transactional
    @Rollback
    public void verificarSession() {
        assertThat(session().isConnected()).isTrue();
    }


    @Autowired
    private RepositorioAPI repositorioAPI;
    private final RestOperations restTemplate = new RestTemplate();

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerUnaListaDeClimas() {
        givenQueExitenClimas();
        List<Clima> climas = whenObtengoLosClimas();
        thenObtengoUnaListaDeClimas(climas);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaListaDeClimas() {
        List<Clima> climaList = givenQueExitenClimasSinGuardar();
        whenGuardoLosClimas(climaList);
        thenCuandoLasObtengoDeberiaNoEstarVacia();
    }

    private void givenQueExitenClimas() {
        List<Clima> climas = obternerClimasDeLaAPI();
        for (Clima clima : climas) {
            session().save(clima);
            session().save(clima.getWeather());
        }
    }

    private List<Clima> givenQueExitenClimasSinGuardar() {
        return obternerClimasDeLaAPI();
    }

    private List<Clima> whenObtengoLosClimas() {
        return repositorioAPI.obtenerTodosLosClimas();
    }

    private void whenGuardoLosClimas(List<Clima> climaList) {
        repositorioAPI.guardarAPI(climaList);
    }

    private List<Clima> obternerClimasDeLaAPI() {
        return Arrays.asList(restTemplate.getForObject("https://ws.smn.gob.ar/map_items/weather", Clima[].class));
    }

    private void thenObtengoUnaListaDeClimas(List<Clima> climas) {
        assertThat(climas).isNotEmpty();
    }

    private void thenCuandoLasObtengoDeberiaNoEstarVacia() {
        assertThat(repositorioAPI.obtenerTodosLosClimas()).isNotEmpty();
    }
}
