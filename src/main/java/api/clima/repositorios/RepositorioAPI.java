package api.clima.repositorios;

import api.clima.modelo.Clima;

import java.util.List;

public interface RepositorioAPI {
    void guardarAPI(List<Clima> climas);
    List<Clima> obtenerTodosLosClimas();
}
