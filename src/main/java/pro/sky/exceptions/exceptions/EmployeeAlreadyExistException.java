package pro.sky.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeAlreadyExistException extends RuntimeException{
    public EmployeeAlreadyExistException() {
        super("Сотрудник уже существует!");
    }
}
