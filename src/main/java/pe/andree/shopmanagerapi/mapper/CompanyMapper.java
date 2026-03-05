package pe.andree.shopmanagerapi.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pe.andree.shopmanagerapi.domain.entities.Company;
import pe.andree.shopmanagerapi.dto.request.company.CompanyRequestDTO;
import pe.andree.shopmanagerapi.dto.response.CompanyResponseDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

    private final ModelMapper modelMapper;

    public CompanyResponseDTO toDTO(Company company){
        return modelMapper.map(company, CompanyResponseDTO.class);
    }

    public List<CompanyResponseDTO> toDTO(List<Company> companies){
        return companies.stream().map(this::toDTO).toList();
    }

    public Company toEntity(CompanyRequestDTO company){
        return modelMapper.map(company, Company.class);
    }

    public List<Company> toEntity(List<CompanyRequestDTO> companies){
        return companies.stream().map(this::toEntity).toList();
    }
}
