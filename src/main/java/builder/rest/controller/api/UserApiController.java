package builder.rest.controller.api;

import builder.rest.domain.request.CreateUserForm;
import builder.rest.domain.response.Response;
import builder.rest.exceptions.BadRequestException;
import builder.rest.logic_layers.rest.UserRestService;
import builder.rest.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Controller
@RequestMapping("/api/users")
public class UserApiController {

    @Autowired
    private UserRestService userRestService;

    @Autowired
    private ResponseBuilder builder;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Response<List<Map<String, Object>>> getUsers(
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,

            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,

            @RequestParam(value = "fields", defaultValue = "id, email", required = false) Set<String> fields
    ) throws BadRequestException {
        return builder.get(userRestService.getUsers(offset, limit, fields));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Response<Map<String, Object>> getUser(
            @PathVariable("id") int id,

            @RequestParam(value = "fields", defaultValue = "id, email", required = false) Set<String> fields
    ) throws BadRequestException {
        return builder.get(userRestService.getUser(id, fields));
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public @ResponseBody
    Response<Boolean> register(
            @RequestBody CreateUserForm form
    ) throws BadRequestException {
        return builder.get(userRestService.registerUser(form));
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public @ResponseBody
    Response<Map<String, Object>> signIn(
            @RequestBody CreateUserForm form
    ) throws BadRequestException {
        return builder.get(userRestService.signIn(form));
    }
}
