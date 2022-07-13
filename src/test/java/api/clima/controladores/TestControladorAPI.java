package api.clima.controladores;

import api.clima.dto.ClimaDTO;
import api.clima.servicios.ServicioAPI;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;;
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
    public void cuando_un_usuario_ingrese_deberia_mostrar_una_lista_climas() {
        givenQueUnUsuarioIngresaAlSitio();
        whenSolicitaVerUnaListaDeClimas();
        thenDeberiaMostrarEsaLista();
    }

    @Test
    public void cuando_solicito_al_servicio_una_lista_de_climas_deberia_devoler_una_lista_de_climas() {
        givenQueElServicioDevueleUnaListaDeClimas();
        whenSolicitaVerUnaListaDeClimas();
        thenDeberiaMostrarEsaLista();
        andVerificarQueSeLlameAlMetodoDelServicio();
    }

    private void givenQueUnUsuarioIngresaAlSitio() {
    }

    private void givenQueElServicioDevueleUnaListaDeClimas() {
        when(servicioAPI.obtenerListaDeClimasDTO()).thenReturn(List.of(new ClimaDTO(), new ClimaDTO()));
    }

    private void whenSolicitaVerUnaListaDeClimas() {
        this.modelAndView = this.controladorAPI.mostrarListaDeClimas(this.modelMap);
    }

    private void thenDeberiaMostrarEsaLista() {
        assertThat(modelAndView.getViewName()).isEqualTo("index");
        assertThat(modelAndView.getModelMap().get("listaDeClimas")).isNotNull();
        assertThat(modelAndView.getModelMap().get("listaDeClimas")).isInstanceOf(List.class);
    }

    private void andVerificarQueSeLlameAlMetodoDelServicio() {
        verify(servicioAPI, times(1)).obtenerListaDeClimasDTO();
    }
}
