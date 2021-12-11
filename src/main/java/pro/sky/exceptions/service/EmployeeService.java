package pro.sky.exceptions.service;

import pro.sky.exceptions.data.Employee;

import java.util.Collection;

public interface EmployeeService {
   Employee addEmployee (String firstName, String lastName);

   Employee addEmployee (Employee employee);

   Employee removeEmployee (String firstName, String lastName);

   Employee removeEmployee (Employee employee);

   Employee findEmployee (String firstName, String lastName);

   Collection<Employee> getAllEmployees();
}
