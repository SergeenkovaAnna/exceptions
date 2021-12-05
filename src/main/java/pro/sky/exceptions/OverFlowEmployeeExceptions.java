package pro.sky.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class OverFlowEmployeeExceptions extends RuntimeException{
    public OverFlowEmployeeExceptions() {
        super("Массив сотрудников переполнен!");
    }
}
