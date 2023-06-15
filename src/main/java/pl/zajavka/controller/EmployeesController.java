package pl.zajavka.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeesController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/add")
    @Transactional
    public String addEmployee(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "salary") String salary
    ) {
        EmployeeEntity newEmployee = EmployeeEntity.builder()
                .name(name)
                .surname(surname)
                .salary(new BigDecimal(salary))
                .build();
        employeeRepository.save(newEmployee);
        return "redirect:/employees";
    }

    @GetMapping
    public String employees(Model model) {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("updateEmployeeDTO", new UpdateEmployeeDTO());
        return "employees";
    }

    @GetMapping("/show/{employeeId}")
    public String showEmployeeDetails(
        @PathVariable Integer employeeId, Model model
    ) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "EMployeeEntity not found, empployeeId: [%s]".formatted((employeeId))
                ));
        model.addAttribute("employee", employeeEntity);
        return "employeeDetails";
    }

    @PutMapping("/update")
    public String updateEmployee(
            @ModelAttribute("updateEmployeeDTO") UpdateEmployeeDTO updateEmployeeDTO
    ) {
        EmployeeEntity employeeEntity = employeeRepository.findById(updateEmployeeDTO.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "EMployeeEntity not found, empployeeId: [%s]".formatted(updateEmployeeDTO.getEmployeeId())
                ));

        employeeEntity.setName(updateEmployeeDTO.getName());
        employeeEntity.setSurname(updateEmployeeDTO.getSurname());
        employeeEntity.setSalary(updateEmployeeDTO.getSalary());
        employeeRepository.save(employeeEntity);
        return "redirect:/employees";
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId)
    {
        employeeRepository.deleteById(employeeId);
        return "redirect:/employees";
    }



}
