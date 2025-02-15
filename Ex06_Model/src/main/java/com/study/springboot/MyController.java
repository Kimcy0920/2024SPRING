package com.study.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception{
        return "Model & View";
    }

    @RequestMapping("/test1")
    public String test1(Model model, @RequestParam("nn") String name){
    	// Model model 뒤에 , @RequestParam("nn") String name 추가 후 "홍길동" -> name으로 변경
    	// @GetMapping -- request.getParameter("nn")이라고 생각하면 됨
    	// 주소창에 http://localhost:8083/test1?nn=홍길동 입력시 test1에 ${name}에 입력한 값이 들어가고 출력됨.
    	
    	// Model 객체를 이용해서, view로 Data 전달
    	// 데이터만 설정이 가능
        model.addAttribute("name", name);

        return "test1";       
    }
     
    @RequestMapping("/mv")
    public ModelAndView test2() {
    	// 데이터와 뷰를 동시에 설정이 가능
        ModelAndView mv = new ModelAndView();
         
        List<String> list = new ArrayList<>();
         
        list.add("test1");
        list.add("test2");
        list.add("test3");
         
        mv.addObject("lists", list);      			 // jstl로 호출
        mv.addObject("ObjectTest", "테스트입니다."); // jstl로 호출
        mv.addObject("name", "홍길동");				 // jstl로 호출
        mv.setViewName("view/myView");
        
        return mv;                                     
    }

}
