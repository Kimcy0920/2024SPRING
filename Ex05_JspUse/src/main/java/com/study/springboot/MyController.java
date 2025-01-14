package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 요청받음. 서블릿에서 디스패쳐
public class MyController {

//  @RequestMapping("/") -> 비교적 최근에 나온 @GetMapping("/")로도 가능
    @GetMapping("/")
    public @ResponseBody String root() throws Exception{
        return "Hi! Hello~"; // @ResponseBody가 있기때문에 웹페이지에 리턴문에 적힌 문구를 출력함
    }
 
    @RequestMapping("/test1")    // localhost:8081/test1
    public String test1() {
        return "test1";          // 실제 호출 될 /WEB-INF/views/test1.jsp     
        // @ResponseBody가 없기 때문에 test1이라는 파일을 리턴
    }
     
    @RequestMapping("/test2")    // localhost:8081/test2
    public String test2() {
        return "sub/test2";      // 실제 호출 될 /WEB-INF/views/sub/test2.jsp       
    }

}
