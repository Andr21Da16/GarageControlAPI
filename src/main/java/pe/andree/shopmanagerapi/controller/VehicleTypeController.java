package pe.andree.shopmanagerapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.andree.shopmanagerapi.dto.ApiResponse;
import pe.andree.shopmanagerapi.dto.request.VehicleTypeRequestDTO;
import pe.andree.shopmanagerapi.dto.response.VehicleTypeResponseDTO;
import pe.andree.shopmanagerapi.service.VehicleTypeService;

import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("vehicle/types")
@Validated
public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<VehicleTypeResponseDTO>>> findAll(
            @PageableDefault(size = 10, sort = "idType") Pageable pageable
    ){
        return ResponseEntity.ok(vehicleTypeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO>> findById(
            @PathVariable @Min(value = 1, message = "The ID must be greater than 0") Long id
    ) {
        return ResponseEntity.ok(vehicleTypeService.findById(id));
    }

    @GetMapping("/by-name")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO>> findByName(
            @RequestParam @NotBlank String name
    ) {
        return ResponseEntity.ok(vehicleTypeService.findByName(name));
    }

    @GetMapping("/by-price")
    public ResponseEntity<ApiResponse<List<VehicleTypeResponseDTO>>> findByPrice(
            @RequestParam @NotNull @Positive Integer price,
            @PageableDefault(size = 10, sort = "idType") Pageable pageable
    ) {
        return ResponseEntity.ok(vehicleTypeService.findByPrice(price, pageable));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO>> addType(
            @Valid @RequestBody VehicleTypeRequestDTO type
    ) {
        ApiResponse<VehicleTypeResponseDTO> response = vehicleTypeService.addType(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<VehicleTypeResponseDTO>> updateType(
            @Valid @PathVariable Long id, @Valid @RequestBody VehicleTypeRequestDTO type){
        ApiResponse<VehicleTypeResponseDTO> response = vehicleTypeService.updateType(id, type);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteType(@PathVariable @Min(1) Long id) {
        vehicleTypeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }


}
