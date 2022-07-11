package api.clima.servicios;

import api.clima.modelo.Clima;

import java.util.List;

public interface ServicioAPI {
    List<Clima> obtenerListadoDeClimasDeLaAPI();
    void guardarListaObtenidaEnELRepositorio(List<Clima> climas);
    List<Clima> obtenerListadoDeClimasDelRepositorio();
}
