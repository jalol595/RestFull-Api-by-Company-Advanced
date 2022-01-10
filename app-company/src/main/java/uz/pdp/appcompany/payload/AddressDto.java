package uz.pdp.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull(message = "Street should not be emptiy")
    private String street;

    @NotNull(message = "homeNumber should not be emptiy")
    private Integer homeNumber;

}
