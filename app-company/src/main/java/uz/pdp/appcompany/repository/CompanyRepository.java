package uz.pdp.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompany.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpNameAndDirectorName(String corpName, String directorName);
}
