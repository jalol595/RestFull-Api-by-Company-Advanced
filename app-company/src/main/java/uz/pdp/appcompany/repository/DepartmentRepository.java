package uz.pdp.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompany.entity.Department;

import javax.persistence.criteria.CriteriaBuilder;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByName(String name);

}
