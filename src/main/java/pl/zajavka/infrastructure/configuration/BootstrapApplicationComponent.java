package pl.zajavka.infrastructure.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;

import java.math.BigDecimal;


@Component
@AllArgsConstructor
public class BootstrapApplicationComponent implements ApplicationListener<ContextRefreshedEvent> {

    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        employeeRepository.deleteAll();
        employeeRepository.save(EmployeeEntity.builder()
                .name("Stefan")
                .surname("Zajavka")
                .salary(new BigDecimal("52322.00"))
                .build());
        employeeRepository.save(EmployeeEntity.builder()
                .name("Agnieszka")
                .surname("Spring")
                .salary(new BigDecimal("62341.00"))
                .build());
        employeeRepository.save(EmployeeEntity.builder()
                .name("Tomasz")
                .surname("Hibernate")
                .salary(new BigDecimal("52231.00"))
                .build());
    }
}
