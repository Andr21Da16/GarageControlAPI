package pe.andree.shopmanagerapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleTypeResponseDTO {

    private Long id;
    private String name;
    private Integer price;
}
