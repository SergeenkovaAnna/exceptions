package pro.sky.exceptions;

public interface EmployeeService {
   boolean addEmployee (String firstName, String lastName);

   boolean deleteEmployee (String firstName, String lastName);

   boolean findEmployee (String firstName, String lastName);
}
