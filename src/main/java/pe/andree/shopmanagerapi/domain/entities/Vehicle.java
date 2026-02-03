package pe.andree.shopmanagerapi.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle", nullable = false)
    private Long idVehicle;

    @Column(name = "name_vehicle", length = 120)
    private String nameVehicle;

    @Column(name = "license_plate", length = 20, nullable = false)
    private String licensePlate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_type", nullable = false, unique = true)
    @JsonBackReference
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_company", nullable = false, unique = true)
    @JsonBackReference
    private Company company;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "id_driver",
            nullable = false,
            unique = true
    )
    private Driver driver;

}
