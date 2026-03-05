package pe.andree.shopmanagerapi.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.andree.shopmanagerapi.domain.entities.Company;
import pe.andree.shopmanagerapi.dto.response.CompanyResponseDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        mapper.createTypeMap(Company.class, CompanyResponseDTO.class)
                .addMappings(m -> {
                    m.map(Company::getIdCompany, CompanyResponseDTO::setId);
                    m.map(Company::getNameCompany, CompanyResponseDTO::setName);
                });

        return mapper;
    }
}