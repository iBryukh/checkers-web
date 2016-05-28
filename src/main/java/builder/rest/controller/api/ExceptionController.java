package builder.rest.controller.api;

import builder.rest.domain.response.Response;
import builder.rest.exceptions.BadRequestException;
import builder.rest.exceptions.ServiceException;
import builder.rest.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
@ControllerAdvice
public class ExceptionController {

    @Autowired
    private ResponseBuilder builder;

    @ExceptionHandler(ServiceException.class)
    public @ResponseBody
    Response handler(ServiceException e){
        return builder.error(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody
    Response handler(BadRequestException e){
        return builder.error(e.getMessage(), e.getCode());
    }

}
