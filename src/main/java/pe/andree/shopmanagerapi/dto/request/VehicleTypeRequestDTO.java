package pe.andree.shopmanagerapi.dto.request;

import jakarta.annotation.Nonnull;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleTypeRequestDTO {

    @NotNull(message = "The vehicle type name cannot be empty.")
    private String name;

    @Positive(message = "The garage price must be a positive value.")
    @NotNull(message = "It is necessary to specify a price for the garage.")
    private Integer price;
}
