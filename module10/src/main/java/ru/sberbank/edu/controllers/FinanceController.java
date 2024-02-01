package ru.sberbank.edu.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ru.sberbank.edu.models.CalcInfo;

@Controller
//@RequestMapping(value="/finance")
public class  FinanceController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

//    @GetMapping("/finance")
//    public ModelAndView info() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/profitability.html");
//        return modelAndView;
//    }

//    @PostMapping("/finance")
//    public ModelAndView calculat(@ModelAttribute CalcInfo calcInfo ) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/result.html");
//        calcInfo.calcResult();
//        modelAndView.addObject("calcInformation", calcInfo);
//
//        return modelAndView;
//    }

    @GetMapping("/finance")
    public String finance(CalcInfo calcInfo, Model model) {
        model.addAttribute("calcInfo", calcInfo );
        return "profitability";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute("calcInfo") @Valid CalcInfo calcInfo, BindingResult bindingResult, Model model ) {
        if (bindingResult.hasErrors()) {
            return "profitability";
        }
        calcInfo.calcResult();
        model.addAttribute("result", calcInfo.getResult());

        return "result";
    }

}
