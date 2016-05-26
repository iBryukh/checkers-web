package builder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
@Controller
public class Index {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
