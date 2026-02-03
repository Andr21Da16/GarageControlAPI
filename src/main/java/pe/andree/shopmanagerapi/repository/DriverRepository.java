package pe.andree.shopmanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.andree.shopmanagerapi.domain.entities.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
