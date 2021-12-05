package pro.sky.exceptions;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final Employee [] employees = new Employee[10];


    @Override
    public boolean addEmployee(String firstName, String lastName) {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] == null) {
                    employees[i] = new Employee (firstName, lastName);
                    return true;
                }
            }
        throw new OverFlowEmployeeExceptions();
    }

    @Override
    public boolean deleteEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == new Employee (firstName, lastName)) {
                employees[i] = null;
                return true;
            }
        }
        throw new NotFoundEmployeeExceptions();
    }

    @Override
    public boolean findEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == new Employee (firstName, lastName)) {
               return true;
            }
        }
        throw new NotFoundEmployeeExceptions();
    }
}
