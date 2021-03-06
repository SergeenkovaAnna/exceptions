package pro.sky.exceptions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.exceptions.data.Employee;
import pro.sky.exceptions.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam String firstName,
                      @RequestParam String lastName,
                      @RequestParam int departmentId,
                      @RequestParam int salary) {
        Employee result = employeeService.addEmployee(firstName, lastName, departmentId, salary);
        return message(result, "успешно добавлен");
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.removeEmployee(firstName, lastName);
        return message(result, "удален");
    }

    @GetMapping("/find")
    public String find(@RequestParam String firstName, @RequestParam String lastName) {
        Employee result = employeeService.findEmployee(firstName, lastName);
        return message(result, "найден");
    }

    @GetMapping("/all")
    public Collection<Employee> allEmployees() {
        return employeeService.getAllEmployees();
    }

    private String message(Employee employee, String status) {
        return String.format("Сотрудник %s %s %s.",
                employee.getFirstName(), employee.getLastName(), status);
    }

}
