package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import api.clima.modelo.Clima;

import java.util.List;

public interface ServicioModelMapper {
    List<ClimaDTO> mapearObejetoDeDominioADTO(List<Clima> climas);
}
