package ru.sberbank.edu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/finance")
public class FinanceController {

    @GetMapping
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/profitability.jsp");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView calculate(@ModelAttribute CalcInfo calcInfo ) {
        calcInfo.calcResult();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/result.jsp");
        modelAndView.addObject("calcInformation", calcInfo);
        return modelAndView;
    }

}
