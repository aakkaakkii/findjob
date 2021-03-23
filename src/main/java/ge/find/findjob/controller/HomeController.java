package ge.find.findjob.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("redirect:" + "index.html");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("redirect:" + "index.html#/login");
    }

    @GetMapping("/public/reset")
    public String resetPage() {
        return "index.html";
    }
}
