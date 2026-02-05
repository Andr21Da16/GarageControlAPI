package pe.andree.shopmanagerapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public ApiResponse<List<VehicleTypeResponseDTO>> findAllWithFilters(
            Integer minPrice,
            Integer maxPrice,
            Integer price,
            String name,
            Pageable pageable
    ) {

        Specification<VehicleType> spec = Specification
                .where(priceBetween(minPrice, maxPrice))
                .and(nameLike(name))
                .and(hasExactPrice(price))
                ;

        Page<VehicleType> page =
                vehicleTypeRepository.findAll(spec, pageable);

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

    public static Specification<VehicleType> priceBetween(
            Integer min, Integer max
    ) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.lessThanOrEqualTo(root.get("price"), max);
            if (max == null) return cb.greaterThanOrEqualTo(root.get("price"), min);
            return cb.between(root.get("price"), min, max);
        };
    }

    public static Specification<VehicleType> nameLike(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return null;
            return cb.like(
                    cb.lower(root.get("nameType")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<VehicleType> hasExactPrice(Integer price) {
        return (root, query, cb) ->
                price == null ? null : cb.equal(root.get("price"), price);
    }
}
