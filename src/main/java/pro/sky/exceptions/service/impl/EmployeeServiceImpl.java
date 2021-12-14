package pro.sky.exceptions.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.exceptions.data.Employee;
import pro.sky.exceptions.exceptions.NotFoundEmployeeException;
import pro.sky.exceptions.exceptions.OverFlowEmployeeException;
import pro.sky.exceptions.service.EmployeeService;

import java.util.Collection;
import java.util.Set;


@Service
public class EmployeeServiceImpl implements EmployeeService {

        private final Set<Employee> employees;

    public EmployeeServiceImpl(Set<Employee> employees) {
        this.employees = employees;
    }


    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmloyee = new Employee(firstName, lastName);
        return addEmployee(newEmloyee);

    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (!employees.add(employee)) {
            throw new OverFlowEmployeeException();
        }
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        return removeEmployee(newEmployee);
    }

    @Override
    public Employee removeEmployee(Employee employee) {
        if (!employees.remove(employee)) {
            throw new NotFoundEmployeeException();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
           throw new NotFoundEmployeeException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Set.copyOf(employees);
    }
}
