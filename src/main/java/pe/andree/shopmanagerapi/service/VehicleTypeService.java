package pe.andree.shopmanagerapi.service;

import pe.andree.shopmanagerapi.dto.request.VehicleTypeRequestDTO;
import pe.andree.shopmanagerapi.dto.response.VehicleTypeResponseDTO;

import java.util.List;

public interface VehicleTypeService {

    List<VehicleTypeResponseDTO> findAll();
    VehicleTypeResponseDTO findById(Long id);
    VehicleTypeResponseDTO findByName(String name);
    VehicleTypeResponseDTO addType(VehicleTypeRequestDTO vehicle);
    VehicleTypeResponseDTO updateType(Long id, VehicleTypeRequestDTO vehicle);
    void deleteType(Long id);
}
