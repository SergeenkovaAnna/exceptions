package pro.sky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEmployeeException extends RuntimeException{
    public NotFoundEmployeeException() {

        super("Сотрудник не найден!");
    }
}