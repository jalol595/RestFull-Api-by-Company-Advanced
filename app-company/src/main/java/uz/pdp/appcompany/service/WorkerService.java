package uz.pdp.appcompany.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.payload.WorkerDto;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;
import uz.pdp.appcompany.repository.WorkerRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * This is method returned List worker
     *
     * @return
     */

    public List<Worker> getWorker() {
        List<Worker> workerList = workerRepository.findAll();
        return workerList;
    }

    /**
     * This is method returned Worker by id
     *
     * @param id
     * @return
     */

    public Worker getByIdWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    /**
     * This is method saved Worker
     *
     * @param workerDto
     * @return
     */


    public Result saveWorker(WorkerDto workerDto) {
        boolean phoneNumber = workerRepository.existsByNameAndPhoneNumber(workerDto.getName(), workerDto.getPhoneNumber());
        boolean numberAndStreet = addressRepository.existsByHomeNumberAndStreet(workerDto.getHomeNumber(), workerDto.getStreet());
        boolean exists = departmentRepository.existsById(workerDto.getDepartmentId());

        if (phoneNumber && numberAndStreet && exists) return new Result("Already exist", false);

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        Department department = optionalDepartment.get();

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(savedAddress);
        worker.setDepartment(department);
        workerRepository.save(worker);
        return new Result("Succsessfull saved", true);
    }


    /**
     * This is method edited Worker
     *
     * @param id
     * @param workerDto
     * @return
     */

    public Result editWorker(Integer id, WorkerDto workerDto) {
        boolean numberNot = workerRepository.existsByNameAndPhoneNumberNot(workerDto.getName(), workerDto.getPhoneNumber());
        boolean exists = departmentRepository.existsById(workerDto.getDepartmentId());

        if (numberNot && exists) return new Result("There is such Phone Number", false);

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        Department department = optionalDepartment.get();

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(savedAddress);
        worker.setDepartment(department);
        workerRepository.save(worker);
        return new Result("Succsessfull edited", true);

    }

    /**
     * This is method edited Worker
     *
     * @param id
     * @return
     */

    public Result deletedWorker(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new Result("Succsessfull deleted", true);
        } catch (Exception e) {
            return new Result("Error!!!", false);
        }

    }


}
