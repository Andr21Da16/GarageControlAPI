package pe.andree.shopmanagerapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.andree.shopmanagerapi.domain.entities.VehicleType;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

    Optional<VehicleType> findByNameTypeIgnoreCase(String nameType);

    Page<VehicleType> findVehicleTypeByPrice(Integer price, Pageable pageable);
}
