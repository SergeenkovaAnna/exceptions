package pro.sky.exceptions.service;

import pro.sky.exceptions.data.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface DepartmentService {
    Employee getEmployeeMinSalary(int departmentId);
    Employee getEmployeeMaxSalary(int departmentId);

    Collection<Employee> getEmployeesFor(int departmentId);
    Map<Integer, List<Employee>> getAllEmployeesByDepartment();
}
