package edu.soft2.controller;

import edu.soft2.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "param1")
public class MyController {
    @RequestMapping("testMaV")
    public ModelAndView testMav(){
        ModelAndView Mav = new ModelAndView();
        System.out.println("MyController.testMaV");
        Mav.setViewName("hello");
        User user = new User();
        user.setUsername("peter");
        Mav.addObject("user",user);
        return Mav;
    }
    @RequestMapping("testForward")
    public String testForward(){
        System.out.println("----testForward()----");
        return "hello";
    }
    @RequestMapping("testRedirect")
    public String testRedirect(){
        System.out.println("----testRedirect()----");
        return "redirect:/WEB-INF/pages/hello.jsp";
    }


  @RequestMapping("/hello.do")
    public String sayHello(){
        System.out.println("hello soringmvc!!");
        //url:/WEB-INF/pages/XXX.jsp
        return "hello";//页面跳转（与视图解析器的前后缀拼接出url）
    }
    @RequestMapping(value = "/param1",method = {RequestMethod.GET,RequestMethod.POST})
    public String param1(HttpServletRequest request){
        System.out.println("-----hello()-----");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = "+username+",age ="+age+";");
        request.setAttribute("username",username);
        request.setAttribute("age",age);
        return "hello";
    }
   @RequestMapping(value = "/param2")
    public String param2(HttpServletRequest request){
        System.out.println("-----hello2()-----");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = "+username+",age ="+age+";");
        request.setAttribute("username",username);
        request.setAttribute("age",age);
        return "redirect:hello";
//       return "forward:hello";
    }
    @RequestMapping(value = "/param3")
    public String param3(HttpServletRequest request, HttpSession session){
        System.out.println("-----hello3()-----");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = "+username+",age ="+age+";");
        session.setAttribute("username",username);
        session.setAttribute("age",age);
        return "hello";
    }

    @RequestMapping(value = "/param4")
    public String param4(String username,int age){
        System.out.println("-----hello4()-----");
//        String username = request.getParameter("username");
//        String age = request.getParameter("age");
//        session.setAttribute("username",username);
//        session.setAttribute("age",age);
        System.out.println("username = "+username+",age ="+age+";");

        return "hello";
    }
    @RequestMapping(value ="/test1")
    public String test1(){
        System.out.println("------test1()--------");

        return "forward:/param2/test3";
    }
    @RequestMapping(value = "/test2")
    public String test2(){
        System.out.println("--------test2()--------");
        return "hello";
    }
    @RequestMapping(value = "/param5")
    public String param5(@RequestParam(value = "username",required = false) String u,@RequestParam(value = "age",required = true) int a){
        System.out.println("-----param5------");
        System.out.println("username="+u+",age="+a);
        return "hello";
    }
    @RequestMapping(value = "/param6")
    public String param6(HttpServletRequest request){
        System.out.println("-----param6()------");
        System.out.println("username="+request.getParameter("username")+",age="+request.getParameter("age"));
        return "hello";
    }

}
