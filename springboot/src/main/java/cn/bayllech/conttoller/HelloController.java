package cn.bayllech.conttoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "tom");
        List<String> nameList = Arrays.asList("aa", "bb", "cc");
        model.addAttribute("nameList", nameList);

        return "index";
    }

}
