package pe.andree.shopmanagerapi.dto.request.company;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyRequestDTO {

    @NotNull(message = "The company name cannot be empty")
    private String name;
    private String phone;

}
