package api.clima.repositorios;

import api.clima.SpringTest;
import api.clima.modelo.Clima;
import api.clima.modelo.Weather;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    @Transactional
    @Rollback
    public void que_se_pueda_obtener_una_lista_de_clima() {
        List<Clima> climas = givenQueExitenClimasGuardados();
        List<Clima> obtenida = whenObtengoTodosLosClimas(climas);
        thenNoDeberiaEstarVacia(obtenida, 5);
    }

    @Test
    @Transactional
    @Rollback
    public void que_se_pueda_persistir_una_lista_de_climas() {
        List<Clima> climas = givenNoExistenClimasGuardados(5);
        whenPersistoLosClimas(climas);
        thenDeberiaPoderObtenerla(climas);
    }

    @Test
    @Transactional
    @Rollback
    public void que_se_pueda_actualizar_una_lista_de_climas() {
        List<Clima> climas = givenQueExitenClimasGuardados();
        List<Clima> actualizada = givenQueTengoDatosParaActualizar();
        whenActualizoLaLista(actualizada);
        thenDeberiaPoderObtenerla(actualizada);
    }

    private void whenActualizoLaLista(List<Clima> actualizada) {
        repositorioAPI.actualizarRepositorio(actualizada);
    }

    private List<Clima> givenQueTengoDatosParaActualizar() {
        List<Clima> climas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Clima clima = new Clima();
            Weather weather = new Weather();
            clima.setId("abc" + i);
            clima.setWeather(weather);
            weather.setTemp(20 + i);
            weather.setTempDesc(clima.getWeather().getTemp() + "°C");
        }
        return climas;
    }

    private List<Clima> givenQueExitenClimasGuardados() {
        List<Clima> climas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Clima clima = new Clima();
            Weather weather = new Weather();
            seteaDatos(i, clima, weather);
            climas.add(clima);
            session().save(weather);
            session().save(clima);
        }
        return climas;
    }

    private void seteaDatos(int i, Clima clima, Weather weather) {
        clima.setId("abc" + i);
        clima.setWeather(weather);
        weather.setTemp(10 + i);
        weather.setTempDesc(clima.getWeather().getTemp() + "°C");
    }

    private List<Clima> givenNoExistenClimasGuardados(int cantidad) {
        List<Clima> climas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            climas.add(new Clima());
        }
        return climas;
    }

    private List<Clima> whenObtengoTodosLosClimas(List<Clima> climas) {
        return repositorioAPI.obtenerTodosLosClimas();
    }

    private void whenPersistoLosClimas(List<Clima> climas) {
        repositorioAPI.persistirDatosDeLaAPI(climas);
    }

    private void thenNoDeberiaEstarVacia(List<Clima> obtenida, int cantidad_esperada) {
        assertThat(obtenida).hasSize(cantidad_esperada);
    }

    private void thenDeberiaPoderObtenerla(List<Clima> climas) {
        assertThat(repositorioAPI.obtenerTodosLosClimas()).isNotEmpty();
    }
}
