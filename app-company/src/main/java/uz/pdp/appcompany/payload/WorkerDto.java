package uz.pdp.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    @NotNull
    private String name;

    @NotNull
    private Integer phoneNumber;

    @NotNull
    private String street;

    @NotNull
    private Integer homeNumber;

    @NotNull
    private Integer departmentId;

}
