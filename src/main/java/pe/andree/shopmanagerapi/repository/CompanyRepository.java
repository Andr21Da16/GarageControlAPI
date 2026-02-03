package pe.andree.shopmanagerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.andree.shopmanagerapi.domain.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
