package builder.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
@Controller
public class Index {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/play_from_json", method = RequestMethod.GET)
    public String playGageFromJSON(){
        return "play_from_json";
    }

    @RequestMapping(value = "/play/{id}", method = RequestMethod.GET)
    public String playGame(
            @PathVariable("id") int id,
            Model model
    ){
        return "play_game";
    }

}
