package api.clima.repositorios;

import api.clima.modelo.Clima;

import java.util.List;

public interface RepositorioAPI {
    List<Clima> obtenerTodosLosClimas();

    void persistirDatosDeLaAPI(List<Clima> climasDeLaAPI);

    void actualizarRepositorio(List<Clima> climas);
}
