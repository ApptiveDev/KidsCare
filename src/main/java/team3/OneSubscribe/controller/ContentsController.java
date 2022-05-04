package team3.OneSubscribe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("content")
public class ContentsController {
    

    @GetMapping("/show_all")
    public String showAll() {

    }
}
