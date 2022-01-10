package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.CompanyDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;


    /**
     * This is method retrun companylist
     *
     * @return companylist
     */

    public List<Company> getCompany() {
        List<Company> companyList = companyRepository.findAll();
        return companyList;
    }

    /**
     * This is method returned Company by id
     *
     * @param id
     * @return Company
     */

    public Company getByIdCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    /**
     * This is method saved Comany
     *
     * @param companyDto
     * @return Result
     */

    public Result saveCompany(CompanyDto companyDto) {
        boolean directorName = companyRepository.existsByCorpNameAndDirectorName(companyDto.getCorpName(), companyDto.getDirectorName());
        boolean existsById = addressRepository.existsByHomeNumberAndStreet(companyDto.getHomeNumber(), companyDto.getStreet());
        if (directorName && existsById) return new Result("Already exsist", false);

        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(saveAddress);
        companyRepository.save(company);
        return new Result("Succsessfull saved company", true);
    }

    /**
     * This is method edting Company
     *
     * @param id
     * @param companyDto
     * @return
     */

    public Result editCompany(Integer id, CompanyDto companyDto) {
        boolean directorName = companyRepository.existsByCorpNameAndDirectorName(companyDto.getCorpName(), companyDto.getDirectorName());
        boolean existsById = addressRepository.existsByHomeNumberAndStreet(companyDto.getHomeNumber(), companyDto.getStreet());
        if (directorName && existsById) return new Result("There is such", false);

        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(saveAddress);
        companyRepository.save(company);
        return new Result("Succsessfull editing company", true);
    }

    /**
     * This is method deleted Company by id
     *
     * @param id
     * @return Result
     */

    public Result deleteCompany(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new Result("Succsessfull deleted company", true);
        } catch (Exception e) {
            return new Result("Error!!!", false);
        }
    }

}
