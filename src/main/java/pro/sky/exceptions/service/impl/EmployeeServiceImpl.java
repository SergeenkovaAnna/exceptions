package pro.sky.exceptions.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.exceptions.data.Employee;
import pro.sky.exceptions.exceptions.BadRequestException;
import pro.sky.exceptions.exceptions.NotFoundEmployeeException;
import pro.sky.exceptions.exceptions.OverFlowEmployeeException;
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
        Employee newEmployee = new Employee(firstName, lastName);
        if (StringUtils.isAlpha(firstName) & StringUtils.isAlpha(lastName)) {
            return addEmployee(newEmployee);
        }
        return addEmployee(firstName,lastName, departmentId, salary);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        String key = getKey(employee);
        if (employees.containsKey(key)) {
            throw new OverFlowEmployeeException();
        }
        isNotAlpha();
        employees.put(StringUtils.capitalize(key), employee);
        return employee;
    }

    private void isNotAlpha() {
        if (!StringUtils.isAlpha((CharSequence) employees)) {
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
        StringUtils.isAlpha((CharSequence) newEmployee);
        return removeEmployee(newEmployee);
    }

    @Override
    public Employee removeEmployee(Employee employee) {
        Employee deletedValue = employees.remove(getKey(employee));
        if (deletedValue == null) {
            throw new NotFoundEmployeeException("Работник отдела " + employee.getDepartmentId() + " не найден");
        }
        isNotAlpha();
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = getKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (employee == null) {
           throw new NotFoundEmployeeException("Работник отдела " + employee.getDepartmentId() + " не найден");
        }
        isNotAlpha();
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployees() {

        return Set.copyOf(employees.values());
    }
}
