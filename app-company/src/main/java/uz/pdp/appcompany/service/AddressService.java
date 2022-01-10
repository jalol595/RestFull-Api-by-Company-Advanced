package uz.pdp.appcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.payload.AddressDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * Get List Address
     * this is method returned addresslist;
     *
     * @return addressList
     */

    public List<Address> getAddress() {
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }

    /**
     * Get by Id
     * This is method returned Address by Id;
     *
     * @param id
     * @return Address
     */

    public Address getByAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    /**
     * This is method  for saved Address
     *
     * @param addressDto
     * @return Result
     */

    public Result saveAddress(AddressDto addressDto) {
        boolean numberAndStreet = addressRepository.existsByHomeNumberAndStreet(addressDto.getHomeNumber(), addressDto.getStreet());
        if (numberAndStreet) return new Result("Already exsist", false);

        Address address = new Address(null, addressDto.getStreet(), addressDto.getHomeNumber());
        addressRepository.save(address);
        return new Result("succsessfull saved address", true);
    }

    /**
     * This is method editing address
     *
     * @param id
     * @param addressDto
     * @return Result
     */

    public Result editAddres(Integer id, AddressDto addressDto) {
        boolean numberNot = addressRepository.existsByHomeNumberNotAndStreet(id, addressDto.getStreet());
        if (numberNot) return new Result("There is such a House Number", false);

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) return new Result("Not found Address", false);

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new Result("Succsessfull edit address", true);
    }

    /**
     * This is method deleting address
     *
     * @param id
     * @return Result
     */

    public Result deleteAddress(Integer id) {
        try {
            addressRepository.deleteById(id);
            return new Result("Succsessfull deleted address", true);

        } catch (Exception e) {
            return new Result("Error !!!", false);
        }
    }
}
