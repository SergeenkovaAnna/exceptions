package pro.sky.exceptions.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.exceptions.data.Employee;
import pro.sky.exceptions.exceptions.BadRequestException;
import pro.sky.exceptions.exceptions.EmployeeAlreadyExistException;
import pro.sky.exceptions.exceptions.NotFoundEmployeeException;
import pro.sky.exceptions.service.EmployeeService;
import pro.sky.exceptions.service.impl.EmployeeServiceImpl;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.exceptions.serviceTest.EmployeeServiceImplConstantForTest.*;

public class EmployeeServiceImplTest {

    private EmployeeService out;

    @BeforeEach
    public void startNew() {
        out = new EmployeeServiceImpl();
    }

    @Test
    void addEmployeeByParameters() {
        Employee expectedEmployee = new Employee(LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1);
        Employee employeeActual = out.addEmployee(LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1);
        assertEquals(expectedEmployee, employeeActual);
    }

    @Test
    void addEmployeeAsObject() {
        Employee expectedEmployee = new Employee(LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1);
        assertEquals(expectedEmployee, out.addEmployee(expectedEmployee));
    }

    @Test
    void addEmployeeAlreadyExistException() {
        Employee employeeActual = new Employee(LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1);
        out.addEmployee(employeeActual);
        assertThrows(EmployeeAlreadyExistException.class, ()-> out.addEmployee(employeeActual));
    }

    public static Stream<Arguments> getIllegalParamsForTests() {
        return Stream.of(
                Arguments.of(new Employee(ILLEGAL_LASTNAME, ILLEGAL_FIRSTNAME, ILLEGAL_DEPARTMENT, SALARY_1)),
                Arguments.of(new Employee(ILLEGAL_LASTNAME, FIRSTNAME_1, DEPARTMENT_1, SALARY_1)),
                Arguments.of(new Employee(LASTNAME_1, ILLEGAL_FIRSTNAME, DEPARTMENT_1, SALARY_1)),
                Arguments.of(new Employee(LASTNAME_1, FIRSTNAME_1, ILLEGAL_DEPARTMENT, SALARY_1)));
    }

    @ParameterizedTest
    @MethodSource("getIllegalParamsForTests")
    void addEmployeeBadRequestException(Employee illegalEmployee) {
        assertThrows(BadRequestException.class, ()-> out.addEmployee(illegalEmployee));
    }

    @Test
    void removeEmployeeTest() {
        Employee employeeActual = out.addEmployee(LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1);
        assertEquals(employeeActual, out.removeEmployee(LASTNAME_1, FIRSTNAME_1));
    }

    @Test
    void removeEmployeeNotFoundEmployeeException() {
        assertThrows(NotFoundEmployeeException.class, ()-> out.removeEmployee(LASTNAME_1, FIRSTNAME_1));
    }


    @Test
    void findEmployeeTest() {
        Employee employeeActual = out.addEmployee(LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1);
        assertEquals(employeeActual, out.findEmployee(LASTNAME_1, FIRSTNAME_1));
    }

    @Test
    void findEmployeeNotFoundEmployeeException() {
        assertThrows(NotFoundEmployeeException.class, ()-> out.findEmployee(LASTNAME_1, FIRSTNAME_1));
    }

    @Test
    void getAllEmployeesTest() {
        assertEquals(0, out.getAllEmployees().size());
        Employee employeeActual_1 = out.addEmployee(LASTNAME_1, FIRSTNAME_1, DEPARTMENT_1, SALARY_1);
        Employee employeeActual_2 = out.addEmployee(LASTNAME_2, FIRSTNAME_2, DEPARTMENT_2, SALARY_2);
        Collection<Employee> allEmployees = out.getAllEmployees();
        Collection<Employee> expectedEmployees = Set.of(employeeActual_1, employeeActual_2);
        assertTrue(expectedEmployees.containsAll(allEmployees) && allEmployees.containsAll(expectedEmployees) && allEmployees.size() == expectedEmployees.size());
    }
}
