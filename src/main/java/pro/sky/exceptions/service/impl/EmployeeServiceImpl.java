package pro.sky.exceptions.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.exceptions.data.Employee;
import pro.sky.exceptions.exceptions.BadRequestException;
import pro.sky.exceptions.exceptions.NotFoundEmployeeException;
import pro.sky.exceptions.exceptions.EmployeeAlreadyExistException;
import pro.sky.exceptions.service.EmployeeService;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {

        private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new LinkedHashMap<>();
    }


    @Override
    public Employee addEmployee(String firstName, String lastName, int departmentId, int salary) {
        checkIfParametersCorrect(firstName, lastName, departmentId);
        Employee newEmployee = new Employee(firstName, lastName, departmentId, salary);
        return addEmployee(newEmployee);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        String key = getKey(employee);
        checkIfParametersCorrect(employee.getFirstName(), employee.getLastName(), employee.getDepartmentId());
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyExistException();
        }
        employees.put(StringUtils.capitalize(key), employee);
        return employee;
    }

    private void checkIfParametersCorrect(String firstName, String lastName, int departmentId) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName) || departmentId <= 0) {
            throw new BadRequestException("Не корректные данные");
        }
    }

    private void checkIfParametersCorrect(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new BadRequestException("Не корректные данные");
        }
    }

    private String getKey(Employee employee) {
        return getKey(employee.getFirstName(), employee.getLastName());
    }

    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        checkIfParametersCorrect(firstName, lastName);
        return removeEmployee(newEmployee);
    }


    private Employee removeEmployee(Employee employee) {
        Employee deletedEmployee = employees.remove(getKey(employee));
        if (deletedEmployee == null) {
            throw new NotFoundEmployeeException("Работник отдела " + employee.getDepartmentId() + " не найден");
        }
        return deletedEmployee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (employee == null) {
           throw new NotFoundEmployeeException("Работник отдела не найден");
        }
        checkIfParametersCorrect(firstName, lastName);
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployees() {

        return Set.copyOf(employees.values());
    }
}
