package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@EnableScheduling
public interface ServicioAPI {
    List<ClimaDTO> obtenerListaDeClimas();
}
