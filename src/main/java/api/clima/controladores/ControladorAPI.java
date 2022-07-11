package api.clima.controladores;

import api.clima.modelo.Clima;
import api.clima.servicios.ServicioAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorAPI {


    private final ServicioAPI servicioAPI;

    @Autowired
    public ControladorAPI(ServicioAPI servicioAPI) {
        this.servicioAPI = servicioAPI;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView mostrarListaDeClimas(ModelMap model) {
        List<Clima> climas = obtenerListaDeClimas();
        model.put("listaDeClimas", climas);
        return new ModelAndView("index", model);
    }

    private List<Clima> obtenerListaDeClimas() {
        return servicioAPI.obtenerListadoDeClimasDelRepositorio();
    }
}

