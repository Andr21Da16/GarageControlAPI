package pe.andree.shopmanagerapi.service;

import org.springframework.data.domain.Page;
import pe.andree.shopmanagerapi.dto.ApiResponse;
import pe.andree.shopmanagerapi.dto.request.VehicleTypeRequestDTO;
import pe.andree.shopmanagerapi.dto.response.VehicleTypeResponseDTO;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleTypeService {

    ApiResponse<List<VehicleTypeResponseDTO>>  findAllWithFilters(
            Integer minPrice,
            Integer maxPrice,
            Integer price,
            String name,
            Pageable pageable
    );
    ApiResponse<VehicleTypeResponseDTO> findById(Long id);
    ApiResponse<VehicleTypeResponseDTO> addType(VehicleTypeRequestDTO vehicle);
    ApiResponse<VehicleTypeResponseDTO> updateType(Long id, VehicleTypeRequestDTO vehicle);

    ApiResponse<Void> deleteType(Long id);
}
