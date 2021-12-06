package pro.sky.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OverFlowEmployeeExceptions extends RuntimeException{
    public OverFlowEmployeeExceptions() {
        super("Массив сотрудников переполнен!");
    }
}
