package pro.sky.exceptions;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final Employee [] employees = new Employee[10];


    @Override
    public Employee addEmployee(String firstName, String lastName) {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] == null) {
                    employees[i] = new Employee (firstName, lastName);
                }
            }
        throw new OverFlowEmployeeExceptions();
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == new Employee (firstName, lastName)) {
                employees[i] = null;
            }
        }
        throw new NotFoundEmployeeException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == new Employee (firstName, lastName)) {
            }
        }
        throw new NotFoundEmployeeException();
    }
}
