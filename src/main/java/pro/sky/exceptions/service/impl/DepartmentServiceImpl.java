package pro.sky.exceptions.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.exceptions.data.Employee;
import pro.sky.exceptions.exceptions.NotFoundEmployeeException;
import pro.sky.exceptions.service.DepartmentService;
import pro.sky.exceptions.service.EmployeeService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeMinSalary(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.isInDepartment(departmentId))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new NotFoundEmployeeException("Работник отдела " + departmentId + " не найден"));
    }

    @Override
    public Employee getEmployeeMaxSalary(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.isInDepartment(departmentId))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new NotFoundEmployeeException("Работник отдела " + departmentId + " не найден"));
    }

    @Override
    public Collection<Employee> getEmployeesFor(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.isInDepartment(departmentId))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
