package uz.pdp.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcompany.entity.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private String corpName;

    @NotNull(message = "direction name should not be empity")
    private String directorName;

    @NotNull(message = "street name should not be empity")
    private String street;

    @NotNull(message = "home number name should not be empity")
    private Integer homeNumber;
}
