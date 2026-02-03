package pe.andree.shopmanagerapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.andree.shopmanagerapi.dto.request.VehicleTypeRequestDTO;
import pe.andree.shopmanagerapi.dto.response.VehicleTypeResponseDTO;
import pe.andree.shopmanagerapi.service.VehicleTypeService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("vehicle/types")
@Validated
public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    @GetMapping
    public ResponseEntity<List<VehicleTypeResponseDTO>> findAll(){
        return ResponseEntity.ok(vehicleTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleTypeResponseDTO> findById(
            @PathVariable @Min(value = 1, message = "The ID must be greater than 0") Long id
    ) {
        return ResponseEntity.ok(vehicleTypeService.findById(id));
    }

    @GetMapping("/by-name")
    public ResponseEntity<VehicleTypeResponseDTO> findByName(
            @RequestParam @NotBlank String name
    ) {
        return ResponseEntity.ok(vehicleTypeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<VehicleTypeResponseDTO> addType(
            @Valid @RequestBody VehicleTypeRequestDTO type
    ) {
        VehicleTypeResponseDTO response = vehicleTypeService.addType(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<VehicleTypeResponseDTO> updateType(
            @Valid @PathVariable Long id, @Valid @RequestBody VehicleTypeRequestDTO type){
        VehicleTypeResponseDTO response = vehicleTypeService.updateType(id, type);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable @Min(1) Long id) {
        vehicleTypeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }


}
