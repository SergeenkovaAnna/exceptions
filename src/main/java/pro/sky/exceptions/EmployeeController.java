package pro.sky.exceptions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.addEmployee(firstName, lastName);
        return message(result, "успешно добавлен");
    }

    @GetMapping("/remove")
    public String delete(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.deleteEmployee(firstName, lastName);
        return message(result, "удален");
    }

    @GetMapping("/find")
    public String find(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.findEmployee(firstName, lastName);
        return message(result, "найден");
    }

    private String message(Employee employee, String status) {
        return String.format("Сотрудник %s %s %s.",
                employee.getFirstName(), employee.getLastName(), status);
    }

}
