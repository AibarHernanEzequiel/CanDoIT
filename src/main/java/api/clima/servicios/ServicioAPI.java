package api.clima.servicios;

import api.clima.modelo.Clima;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@EnableScheduling
public interface ServicioAPI {
    List<Clima> obtenerListadoDeClimasDeLaAPI();

    void guardarListaObtenidaEnELRepositorio(List<Clima> climas);

    List<Clima> obtenerListadoDeClimasDelRepositorio();

    void consumirYPersistirAPICada5Minutos();
}
