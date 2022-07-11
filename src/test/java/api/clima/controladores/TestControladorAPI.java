package api.clima.controladores;

import api.clima.modelo.Clima;
import api.clima.servicios.ServicioAPI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TestControladorAPI {

    private ControladorAPI controladorAPI;
    private ModelAndView modelAndView;
    private ModelMap modelMap;
    private ServicioAPI servicioAPI;

    @Before
    public void setup() {
        servicioAPI = mock(ServicioAPI.class);
        controladorAPI = new ControladorAPI(servicioAPI);
        modelAndView = new ModelAndView();
        modelMap = new ModelMap();
    }

    @Test
    public void cuandoUnUsuarioSoliciteVerlosClimasDeberiaMostrarseUnaListaConClimas() {
        givenQueUnUsuarioIngresaAlSitio();
        whenSolicitaVerEListadoDeClimas();
        thenDeberiaMostrarUnListadoDeClimas(this.modelAndView);
    }

    @Test
    public void cuandoPidoAlServicioUnaListaDeClimasDeberiaObtenerUnaListaYVerificarQueSeLLameAlMetodoDelServicio() {
        givenDadoQueSolicitoAlServicioUnaListaDeClimas();
        whenSolicitaVerEListadoDeClimas();
        thenDeberiaMostrarUnListadoDeClimas(this.modelAndView);
        andVerificarQueSeLlameAlMetodoDelServicio();
    }

    private void givenQueUnUsuarioIngresaAlSitio() {

    }

    private void givenDadoQueSolicitoAlServicioUnaListaDeClimas() {
        List<Clima> climas = obtenerClimas();
        when(servicioAPI.obtenerListadoDeClimasDelRepositorio()).thenReturn(climas);
    }

    private List<Clima> obtenerClimas() {
        List<Clima> climas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            climas.add(new Clima());
        }
        return climas;
    }

    private void whenSolicitaVerEListadoDeClimas() {
        this.modelAndView = controladorAPI.mostrarListaDeClimas(this.modelMap);
    }


    private void thenDeberiaMostrarUnListadoDeClimas(ModelAndView modelAndView) {
        assertThat(modelAndView.getViewName()).isEqualTo("index");
        assertThat(modelAndView.getModelMap().get("listaDeClimas")).isNotNull();
        assertThat(modelAndView.getModelMap().get("listaDeClimas")).isInstanceOf(List.class);
    }

    private void andVerificarQueSeLlameAlMetodoDelServicio() {
        verify(servicioAPI, times(1)).obtenerListadoDeClimasDelRepositorio();
    }
}
