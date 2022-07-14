package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@EnableScheduling
public interface ServicioAPI {
    List<ClimaDTO> consumirAPI();

    void guardarDatosObtenidosDeLaAPI(List<ClimaDTO> climaDTOList);

    List<ClimaDTO> obtenerListaDeClimasDTO();

    void actualizarRepositorio(List<ClimaDTO> climaDTOList);
}
