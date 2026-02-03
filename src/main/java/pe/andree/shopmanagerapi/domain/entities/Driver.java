package pe.andree.shopmanagerapi.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_driver", nullable = false)
    private Long idDriver;

    @Column(name = "name_driver", length = 120, nullable = false)
    private String nameDriver;

    @Column(name = "phone", length = 20,unique = true)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_company", nullable = false, unique = true)
    @JsonBackReference
    private Company company;

    @OneToOne(mappedBy = "driver")
    private Vehicle vehicle;
}
