package edu.soft2.controller;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.soft2.pojo.User;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.io.*;
import java.util.UUID;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        System.out.println("----logout()-----");
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("session失效");
        String msg = "你已退出登录！";
        request.setAttribute("msg",msg);
        return "forward:/index.jsp";
    }

    //删除操作
    @RequestMapping(value = "/delete")
    public String delete(){
        System.out.println("----delete()----");
        return "hello";
    }

    //登录
    @RequestMapping(value = "login")
    public String login(User user, HttpSession session){
        System.out.println("----login()----");
        int flag = 1;//调用业务层,获取返回值
        if (flag == 1) {
            session.setAttribute("user",user);
            return "welcome";
        }
        return "hello";
    }

    @RequestMapping(value = "hello")
    public String hello(){
        System.out.println("----hello()----");
        return "hello";
    }

    @RequestMapping(value = "upload",method = {RequestMethod.POST})
    public String upload(MultipartFile[] image,HttpServletRequest request) throws IOException, IOException {
        System.out.println("--------upload-------");
        InputStream is = null;
        OutputStream os = null;
        for(MultipartFile imageFile:image){
            //获取文件的输入流对象
             is = imageFile.getInputStream();
            //获取文件名称
            String  filename = imageFile.getOriginalFilename();
            if (filename.equals("")) {
                System.out.println("空白字符串，进入下一轮循环");
                continue;
            }
            //设置上传路径
            String realPath = request.getServletContext().getRealPath("/uploads");
            System.out.println("上传路径="+realPath);
            //设置上传server后的新名称TBD
            String newname = doFilename(filename);
            os = new FileOutputStream(new File(realPath,newname));
            int size = IOUtils.copy(is,os);
            System.out.println("上传"+filename+"到（"+realPath+"）完毕共计"+size+"字节,上传后文件名称为"+newname);
            //资源释放（原则：先开后关）

        }
        os.close();is.close();
        return "welcome";
    }
    private String doFilename(String filename){
         String extension = FilenameUtils.getExtension(filename);
        String uuid = UUID.randomUUID().toString();

        return uuid+"."+extension;
    }
}