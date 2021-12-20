package pro.sky.exceptions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.exceptions.data.Employee;
import pro.sky.exceptions.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departmentId")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/min-salary")
    Employee getEmployeeMinSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeMinSalary(departmentId);
    }

    @GetMapping("/max-salary")
    Employee getEmployeeMaxSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeMinSalary(departmentId);
    }

    @GetMapping("/all")
    Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return departmentService.getAllEmployeesByDepartment();
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    Collection<Employee> getEmployeesFor(@RequestParam int departmentId) {
        return departmentService.getEmployeesFor(departmentId);
    }
}
