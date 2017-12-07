package koreatech.cse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String home(Model model) {
        model.addAttribute("textFromController", "World");
        return "main";
    }

}
