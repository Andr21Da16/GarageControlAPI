package pe.andree.shopmanagerapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.andree.shopmanagerapi.domain.entities.VehicleType;
import pe.andree.shopmanagerapi.dto.ApiResponse;
import pe.andree.shopmanagerapi.dto.request.VehicleTypeRequestDTO;
import pe.andree.shopmanagerapi.dto.response.VehicleTypeResponseDTO;
import pe.andree.shopmanagerapi.exceptions.DuplicateVehicleTypeException;
import pe.andree.shopmanagerapi.exceptions.VehicleTypeNotFoundException;
import pe.andree.shopmanagerapi.mapper.MetaMapper;
import pe.andree.shopmanagerapi.mapper.VehicleTypeMapper;
import pe.andree.shopmanagerapi.repository.VehicleTypeRepository;
import pe.andree.shopmanagerapi.service.VehicleTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeMapper vehicleTypeMapper;
    private final MetaMapper metaMapper;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<VehicleTypeResponseDTO>> findAll(Pageable pageable) {

        Page<VehicleType> page = vehicleTypeRepository.findAll(pageable);

        Page<VehicleTypeResponseDTO> dtoPage =
                page.map(vehicleTypeMapper::toDTO);

        return ApiResponse.<List<VehicleTypeResponseDTO>>builder()
                .success(true)
                .message("Vehicle types retrieved successfully")
                .data(dtoPage.getContent())
                .meta(metaMapper.fromPage(dtoPage))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<VehicleTypeResponseDTO> findById(Long id) {

        VehicleType vehicleType = vehicleTypeRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleTypeNotFoundException(
                                "Vehicle type not found with ID: " + id
                        )
                );

        return ApiResponse.<VehicleTypeResponseDTO>builder()
                .success(true)
                .message("Vehicle type retrieved successfully")
                .data(vehicleTypeMapper.toDTO(vehicleType))
                .meta(null)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<VehicleTypeResponseDTO> findByName(String name) {

        VehicleType vehicleType = vehicleTypeRepository
                .findByNameTypeIgnoreCase(name)
                .orElseThrow(() ->
                        new VehicleTypeNotFoundException(
                                "Vehicle type not found with name: " + name
                        )
                );

        return ApiResponse.<VehicleTypeResponseDTO>builder()
                .success(true)
                .message("Vehicle type retrieved successfully")
                .data(vehicleTypeMapper.toDTO(vehicleType))
                .meta(null)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<VehicleTypeResponseDTO>> findByPrice(
            Integer price, Pageable pageable
    ) {

        Page<VehicleType> page =
                vehicleTypeRepository.findVehicleTypeByPrice(price, pageable);

        Page<VehicleTypeResponseDTO> dtoPage =
                page.map(vehicleTypeMapper::toDTO);

        return ApiResponse.<List<VehicleTypeResponseDTO>>builder()
                .success(true)
                .message("Vehicle types retrieved successfully")
                .data(dtoPage.getContent())
                .meta(metaMapper.fromPage(dtoPage))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<VehicleTypeResponseDTO> addType(
            VehicleTypeRequestDTO vehicle
    ) {

        VehicleType vehicleType =
                vehicleTypeMapper.toEntity(vehicle);

        try {
            vehicleType = vehicleTypeRepository.save(vehicleType);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateVehicleTypeException(
                    "Vehicle type already exists"
            );
        }

        return ApiResponse.<VehicleTypeResponseDTO>builder()
                .success(true)
                .message("Vehicle type created successfully")
                .data(vehicleTypeMapper.toDTO(vehicleType))
                .meta(null)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<VehicleTypeResponseDTO> updateType(
            Long id, VehicleTypeRequestDTO vehicle
    ) {

        VehicleType existing = vehicleTypeRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleTypeNotFoundException(
                                "Vehicle type not found"
                        )
                );

        existing.setNameType(vehicle.getName());
        existing.setPrice(vehicle.getPrice());

        try {
            existing = vehicleTypeRepository.save(existing);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateVehicleTypeException(
                    "Vehicle type already exists"
            );
        }

        return ApiResponse.<VehicleTypeResponseDTO>builder()
                .success(true)
                .message("Vehicle type updated successfully")
                .data(vehicleTypeMapper.toDTO(existing))
                .meta(null)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteType(Long id) {

        VehicleType existing = vehicleTypeRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleTypeNotFoundException(
                                "Vehicle type not found"
                        )
                );

        vehicleTypeRepository.delete(existing);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Vehicle type deleted successfully")
                .data(null)
                .meta(null)
                .build();
    }
}
