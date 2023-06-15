package pl.zajavka.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeDTO {
    private Integer employeeId;
    private String name;
    private String surname;
    private BigDecimal salary;
}
