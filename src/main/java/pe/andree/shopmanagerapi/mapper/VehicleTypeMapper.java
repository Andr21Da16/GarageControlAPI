package pe.andree.shopmanagerapi.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pe.andree.shopmanagerapi.domain.entities.VehicleType;
import pe.andree.shopmanagerapi.dto.request.VehicleTypeRequestDTO;
import pe.andree.shopmanagerapi.dto.response.VehicleTypeResponseDTO;

import java.util.List;

@RequiredArgsConstructor
@Component
public class VehicleTypeMapper {

    private final ModelMapper modelMapper;

    public VehicleTypeResponseDTO toDTO(VehicleType vehicleType){
        return modelMapper.map(vehicleType, VehicleTypeResponseDTO.class);
    }

    public List<VehicleTypeResponseDTO> toDTO(List<VehicleType> vehicleTypes){
        return vehicleTypes.stream().map(this::toDTO).toList();
    }

    public VehicleType toEntity(VehicleTypeRequestDTO vehicleTypeRequestDTO){
        return modelMapper.map(vehicleTypeRequestDTO, VehicleType.class);
    }

    public List<VehicleType> toEntity(List<VehicleTypeRequestDTO> vehicleTypeRequestDTO){
        return vehicleTypeRequestDTO.stream().map(this::toEntity).toList();
    }

}
