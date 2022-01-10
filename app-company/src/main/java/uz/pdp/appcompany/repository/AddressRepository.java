package uz.pdp.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompany.entity.Address;

import javax.persistence.criteria.CriteriaBuilder;

public interface AddressRepository extends JpaRepository<Address, Integer> {
   boolean existsByHomeNumberAndStreet(Integer homeNumber, String street);
   boolean existsByHomeNumberNotAndStreet(Integer homeNumber, String street);
}
