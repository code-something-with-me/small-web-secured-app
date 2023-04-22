package code.with.me;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final ProductService service;

    @GetMapping("/main")
    public String mainPage(Authentication a, Model m) {
        m.addAttribute("username", a.getName());
        m.addAttribute("products", service.findAll());
        return "main.th";
    }



}
