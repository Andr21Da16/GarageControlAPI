package pe.andree.shopmanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.andree.shopmanagerapi.domain.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
