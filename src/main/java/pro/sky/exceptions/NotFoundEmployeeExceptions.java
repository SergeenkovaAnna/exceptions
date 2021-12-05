package pro.sky.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NotFoundEmployeeExceptions extends RuntimeException{
    public NotFoundEmployeeExceptions() {
        super("Сотрудник не найден!");
    }
}
