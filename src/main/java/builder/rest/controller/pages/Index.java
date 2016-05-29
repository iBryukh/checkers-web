package builder.rest.controller.pages;

import builder.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
@Controller
public class Index {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        userService.load(1);
        return "index";
    }

}
