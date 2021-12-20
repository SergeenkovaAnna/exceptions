package pro.sky.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEmployeeException extends RuntimeException{
    public NotFoundEmployeeException(String s) {

        super("Сотрудник не найден!");
    }
}
