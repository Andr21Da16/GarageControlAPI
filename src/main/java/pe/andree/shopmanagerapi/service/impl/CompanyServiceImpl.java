package pe.andree.shopmanagerapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pe.andree.shopmanagerapi.domain.entities.Company;
import pe.andree.shopmanagerapi.dto.ApiResponse;
import pe.andree.shopmanagerapi.dto.request.company.CompanyRequestDTO;
import pe.andree.shopmanagerapi.dto.response.CompanyResponseDTO;
import pe.andree.shopmanagerapi.exceptions.global.DuplicateException;
import pe.andree.shopmanagerapi.exceptions.company.CompanyNotFoundException;

import pe.andree.shopmanagerapi.mapper.CompanyMapper;
import pe.andree.shopmanagerapi.mapper.MetaMapper;
import pe.andree.shopmanagerapi.repository.CompanyRepository;
import pe.andree.shopmanagerapi.repository.VehicleTypeRepository;
import pe.andree.shopmanagerapi.service.CompanyService;
import pe.andree.shopmanagerapi.utils.SpecificationUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;
    private final MetaMapper metaMapper;

    @Override
    public ApiResponse<List<CompanyResponseDTO>> findFindAllWithFilters(
            String name,
            String phone,
            Pageable pageable) {

        Specification<Company> spec =
                Specification.<Company>where(
                                SpecificationUtil.stringLike("nameCompany", name)
                        )
                        .and(SpecificationUtil.stringLike("phone", phone));

        Page<Company> page =
                companyRepository.findAll(spec, pageable);

        Page<CompanyResponseDTO> dtoPage =
                page.map(companyMapper::toDTO);

        return ApiResponse.<List<CompanyResponseDTO>>builder()
                .success(true)
                .message("Vehicle types retrieved successfully")
                .data(dtoPage.getContent())
                .meta(metaMapper.fromPage(dtoPage))
                .build();
    }


    @Override
    public ApiResponse<CompanyResponseDTO> findById(Long id) {

        Company company = companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFoundException("Company not found with ID: \" + id")
        );

        return ApiResponse.<CompanyResponseDTO>builder()
                .success(true)
                .message("Company retrieved successfully")
                .data(companyMapper.toDTO(company))
                .meta(null)
                .build();

    }

    @Override
    public ApiResponse<CompanyResponseDTO> addCompany(CompanyRequestDTO company) {


        Company aux = companyMapper.toEntity(company);
        try {
            aux = companyRepository.save(aux);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(
                    "Vehicle type already exists"
            );
        }
        return ApiResponse.<CompanyResponseDTO>builder()
                .success(true)
                .message("Company saved successfully")
                .data(companyMapper.toDTO(aux))
                .meta(null)
                .build();

    }

    @Override
    public ApiResponse<CompanyResponseDTO> updateCompany(Long id, CompanyRequestDTO company) {
        Company aux = companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFoundException("Company not found")
        );

        aux.setNameCompany(company.getName());
        aux.setPhone(company.getPhone());
        try {
            aux = companyRepository.save(aux);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateException(
                    "Vehicle type already exists"
            );
        }

        return ApiResponse.<CompanyResponseDTO>builder()
                .success(true)
                .message("Company updated successfully")
                .data(companyMapper.toDTO(aux))
                .meta(null)
                .build();


    }


    @Override
    public ApiResponse<Void> delete(Long id) {


        Company company = companyRepository.findById(id).orElseThrow(
                () -> new CompanyNotFoundException("Company not found with ID: \" + id")
        );

        companyRepository.delete(company);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Company deleted successfully")
                .data(null)
                .meta(null)
                .build();
    }


}
