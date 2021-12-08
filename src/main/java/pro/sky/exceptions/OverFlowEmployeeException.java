package pro.sky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OverFlowEmployeeException extends RuntimeException{
    public OverFlowEmployeeException() {
        super("Массив сотрудников переполнен!");
    }
}
