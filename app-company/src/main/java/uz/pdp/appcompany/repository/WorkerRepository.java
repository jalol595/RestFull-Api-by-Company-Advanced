package uz.pdp.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcompany.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByNameAndPhoneNumber(String name, Integer phoneNumber);
    boolean existsByNameAndPhoneNumberNot(String name, Integer phoneNumber);
}
