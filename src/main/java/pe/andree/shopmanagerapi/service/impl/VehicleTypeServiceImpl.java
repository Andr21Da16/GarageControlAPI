package pe.andree.shopmanagerapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.andree.shopmanagerapi.domain.entities.VehicleType;
import pe.andree.shopmanagerapi.dto.request.VehicleTypeRequestDTO;
import pe.andree.shopmanagerapi.dto.response.VehicleTypeResponseDTO;
import pe.andree.shopmanagerapi.exceptions.DuplicateVehicleTypeException;
import pe.andree.shopmanagerapi.exceptions.VehicleTypeNotFoundException;
import pe.andree.shopmanagerapi.mapper.VehicleTypeMapper;
import pe.andree.shopmanagerapi.repository.VehicleTypeRepository;
import pe.andree.shopmanagerapi.service.VehicleTypeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeMapper vehicleTypeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<VehicleTypeResponseDTO> findAll() {
        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
        return vehicleTypeMapper.toDTO(vehicleTypes);
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleTypeResponseDTO findById(Long id) {

        VehicleType vehicleType = vehicleTypeRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleTypeNotFoundException("Vehicle type not found with ID: " + id)
                );

        return vehicleTypeMapper.toDTO(vehicleType);
    }

    @Override
    public VehicleTypeResponseDTO findByName(String name) {
        VehicleType vehicleType = vehicleTypeRepository.findByNameTypeIgnoreCase(name)
                .orElseThrow(() ->
                        new VehicleTypeNotFoundException("Vehicle type not found with name: " + name)
                );

        return vehicleTypeMapper.toDTO(vehicleType);
    }

    @Override
    @Transactional
    public VehicleTypeResponseDTO addType(VehicleTypeRequestDTO vehicle) {
        VehicleType vehicleType = vehicleTypeMapper.toEntity(vehicle);

        try {
            vehicleType = vehicleTypeRepository.save(vehicleType);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateVehicleTypeException("Vehicle type already exists");
        }
        return vehicleTypeMapper.toDTO(vehicleType);
    }

    @Override
    @Transactional
    public VehicleTypeResponseDTO updateType(Long id,VehicleTypeRequestDTO vehicle) {

        VehicleType existing = vehicleTypeRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleTypeNotFoundException("Vehicle type not found")
                );


        existing.setNameType(vehicle.getName());
        existing.setPrice(vehicle.getPrice());

        try {
            existing = vehicleTypeRepository.save(existing);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateVehicleTypeException("Vehicle type already exists");
        }

        return vehicleTypeMapper.toDTO(existing);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {

        VehicleType existing = vehicleTypeRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleTypeNotFoundException("Vehicle type not found")
                );

        vehicleTypeRepository.delete(existing);
    }



}
