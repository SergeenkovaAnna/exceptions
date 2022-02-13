package pro.sky.exceptions.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.exceptions.data.Employee;
import pro.sky.exceptions.exceptions.NotFoundEmployeeException;
import pro.sky.exceptions.service.impl.DepartmentServiceImpl;
import pro.sky.exceptions.service.impl.EmployeeServiceImpl;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.exceptions.serviceTest.EmployeeServiceImplConstantForTest.*;
import static pro.sky.exceptions.serviceTest.EmployeeServiceImplConstantForTest.SALARY_2;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;

    Employee employeeActual_1 = new Employee (LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1);
    Employee employeeActual_2 = new Employee (LASTNAME_3, FIRSTNAME_3, DEPARTMENT_1, SALARY_3);

    @BeforeEach
    public void startNew() {
       Collection<Employee> allEmployees = Set.of(employeeActual_1, employeeActual_2);
       when(employeeService.getAllEmployees()).thenReturn(allEmployees);
    }

    @Test
    void getEmployeeMinSalaryTest() {
        assertEquals(employeeActual_1, out.getEmployeeMinSalary(DEPARTMENT_1));
    }

    @Test
    void getEmployeeMinSalaryNotFoundEmployeeExceptionTest() {
        assertThrows(NotFoundEmployeeException.class, () -> out.getEmployeeMinSalary(DEPARTMENT_2));
    }

    @Test
    void getEmployeeMaxSalaryTest() {
        assertEquals(employeeActual_2, out.getEmployeeMaxSalary(DEPARTMENT_1));
    }

    @Test
    void getEmployeeMaxSalaryNotFoundEmployeeExceptionTest() {
        assertThrows(NotFoundEmployeeException.class, () -> out.getEmployeeMaxSalary(DEPARTMENT_2));
    }

    @Test
    void getEmployeesForTest() {
        Employee employeeActual_1 = employeeService.addEmployee(LASTNAME_2, FIRSTNAME_2, DEPARTMENT_2, SALARY_2);
        Employee employeeActual_2 = employeeService.addEmployee(LASTNAME_3, FIRSTNAME_3, DEPARTMENT_2, SALARY_3 );
        Employee employeeActual_3 = employeeService.addEmployee(LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1 );
        Collection<Employee> allEmployeesAtDepartment = out.getEmployeesFor(DEPARTMENT_2);
        Collection<Employee> expectedEmployees = Set.of(employeeActual_1, employeeActual_2);
        assertTrue(expectedEmployees.containsAll(allEmployeesAtDepartment)
          && allEmployeesAtDepartment.containsAll(expectedEmployees)
          && allEmployeesAtDepartment.size()
          == expectedEmployees.size());
    }

    @Test
    void getAllEmployeesByDepartmentTest() {
        Collection<Employee> actual = (Collection<Employee>) out.getAllEmployeesByDepartment();
        Collection<Employee> expected = Set.of(employeeActual_1, employeeActual_2);
        assertEquals(expected.size(), actual.size());
    }

}
