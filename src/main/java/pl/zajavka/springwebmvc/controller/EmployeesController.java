package pl.zajavka.springwebmvc.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zajavka.springwebmvc.infrastructure.database.repository.EmployeeRepository;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeesController {

    private final EmployeeRepository employeeRepository;



}
