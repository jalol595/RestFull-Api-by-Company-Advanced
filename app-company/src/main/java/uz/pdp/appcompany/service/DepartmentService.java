package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.payload.DepartmentDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.repository.CompanyRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    /**
     * This is method returned ListDepartment
     *
     * @return ListDepartment
     */

    public List<Department> getDepartment() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
    }

    /**
     * This is method returned Department By Id
     *
     * @param id
     * @return Department By Id
     */

    public Department getByIdDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }


    /**
     * This is method saved Department
     *
     * @param departmentDto
     * @return Result
     */

    public Result saveDepartment(DepartmentDto departmentDto) {
        boolean existsByName = departmentRepository.existsByName(departmentDto.getName());
        boolean existsById = companyRepository.existsById(departmentDto.getCompanyId());
        if (existsByName && existsById) return new Result("Already exist", false);

        Optional<Company> company = companyRepository.findById(departmentDto.getCompanyId());
        Company companyId = company.get();

        Department department = new Department(null, departmentDto.getName(), companyId);
        departmentRepository.save(department);
        return new Result("Succsessfull saved department", true);
    }

    /**
     * This is method edited Department
     *
     * @param id
     * @param departmentDto
     * @return
     */

    public Result editDepartment(Integer id, DepartmentDto departmentDto) {
        boolean existsByName = departmentRepository.existsByName(departmentDto.getName());
        boolean existsById = companyRepository.existsById(departmentDto.getCompanyId());
        if (existsByName && existsById) return new Result("There is such department", false);

        Optional<Company> company = companyRepository.findById(departmentDto.getCompanyId());
        Company companyId = company.get();

        Department department = new Department(null, departmentDto.getName(), companyId);
        departmentRepository.save(department);
        return new Result("Succsessfull edited department", true);

    }

    /**
     * This is method deleted Department
     *
     * @param id
     * @return
     */

    public Result deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new Result("Succsessfull deleted department", true);
        } catch (Exception e) {
            return new Result("Error!!", false);
        }
    }

}
