package pe.andree.shopmanagerapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.andree.shopmanagerapi.dto.ApiResponse;
import pe.andree.shopmanagerapi.dto.request.company.CompanyRequestDTO;
import pe.andree.shopmanagerapi.dto.response.CompanyResponseDTO;

import pe.andree.shopmanagerapi.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Validated
public class        CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CompanyResponseDTO>>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            Pageable pageable
    ) {

         ApiResponse<List<CompanyResponseDTO>> data = companyService.findFindAllWithFilters(
                name, phone, pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponseDTO>> findById(@PathVariable Long id){
        ApiResponse<CompanyResponseDTO> data = companyService.findById(id);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyResponseDTO>> addCompany(@Valid @RequestBody CompanyRequestDTO request) {
       ApiResponse<CompanyResponseDTO> response =  companyService.addCompany(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse<CompanyResponseDTO>> updateCompany(
            @PathVariable Long id, @RequestBody CompanyRequestDTO request){
        ApiResponse<CompanyResponseDTO> response = companyService.updateCompany(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletedCompany(@PathVariable @Positive Long id){
        ApiResponse<Void> response = companyService.delete(id);
        return ResponseEntity.ok(response);
    }
}
