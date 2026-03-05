package pe.andree.shopmanagerapi.service;

import org.springframework.data.domain.Pageable;
import pe.andree.shopmanagerapi.dto.ApiResponse;
import pe.andree.shopmanagerapi.dto.request.company.CompanyRequestDTO;
import pe.andree.shopmanagerapi.dto.response.CompanyResponseDTO;

import java.util.List;

public interface CompanyService {
    ApiResponse<List<CompanyResponseDTO>> findFindAllWithFilters(
            String name,
            String phone,
            Pageable pageable
    );
    ApiResponse<CompanyResponseDTO> findById(Long id);
    ApiResponse<CompanyResponseDTO> addCompany(CompanyRequestDTO company);
    ApiResponse<CompanyResponseDTO> updateCompany(Long id, CompanyRequestDTO company);
    ApiResponse<Void> delete(Long id);
}
