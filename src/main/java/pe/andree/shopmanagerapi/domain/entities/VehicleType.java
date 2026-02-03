package pe.andree.shopmanagerapi.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "vehicle_types")
@Data
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type", nullable = false)
    private Long idType;

    @Column(name = "name_type", length = 120, nullable = false)
    private String nameType;

    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToMany(
            mappedBy = "vehicleType",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Vehicle> vehicles;


}
