package api.clima.servicios;

import api.clima.dto.ClimaDTO;
import api.clima.modelo.Clima;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class ServicioModelMapperImpl implements ServicioModelMapper {

    @Autowired
    private ModelMapper modelMapper;
    private List<ClimaDTO> climaDTOList = new ArrayList<>();

    public ServicioModelMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClimaDTO> mapearObejetoDeDominioADTO(List<Clima> climas) {
        convertirObjetoDeDominioAClimaDTO(climas, climaDTOList);
        return climaDTOList;
    }

    private void convertirObjetoDeDominioAClimaDTO(List<Clima> climas, List<ClimaDTO> climaDTOList) {
        climas.stream().map(clima -> modelMapper.map(clima, ClimaDTO.class)).forEach(climaDTOList::add);
    }
}
