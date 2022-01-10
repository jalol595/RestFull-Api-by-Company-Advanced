package uz.pdp.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appcompany.entity.Company;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {


    @NotNull(message = "name should not be empity")
    private String name;

    @NotNull(message = "company id  should not be empity")
    private Integer companyId;
}
