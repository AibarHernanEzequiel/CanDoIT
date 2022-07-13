package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import api.clima.modelo.Clima;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


public class ServicioModelMapper {

    private final ModelMapper modelMapper = new ModelMapper();
    private final List<ClimaDTO> climaDTOList = new ArrayList<>();
    private final List<Clima> climas = new ArrayList<>();


    public List<ClimaDTO> mapearObejetoDeDominioADTO(List<Clima> climasList) {
        climasList.stream().map(clima -> modelMapper.map(clima, ClimaDTO.class)).forEach(climaDTOList::add);
        return climaDTOList;
    }

    public List<Clima> mapearDTOAObjetoDeDominio(List<ClimaDTO> climaDTOList) {
        climaDTOList.stream().map(climaDTO -> modelMapper.map(climaDTO, Clima.class)).forEach(climas::add);
        return climas;
    }
}
