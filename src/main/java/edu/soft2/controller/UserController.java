package edu.soft2.controller;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.soft2.pojo.User;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.*;
import java.net.URLEncoder;
import java.util.Locale;
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
    @RequestMapping(value = "/upload2",method = {RequestMethod.POST})
    public String upload2(MultipartFile[] image,HttpServletRequest request) throws IOException {
        System.out.println("----upload2()----");
        InputStream is = null; OutputStream os = null;
        for (MultipartFile imageFile:image) { //单个文件上传
            System.out.println("UserController.upload2.foreach");
            //获取文件输入流对象
            is = imageFile.getInputStream();
            //获取文件名称
            String filename = imageFile.getOriginalFilename();
            //设置上传路径
            String realPath = request.getServletContext().getRealPath("/uploads");
            System.out.println("上传路径="+realPath);
            //设置上传server后的新名称
            String newname = doFilename(filename);
            os = new FileOutputStream(new File(realPath,newname));
            int size = IOUtils.copy(is,os);//完成文件拷贝的大小(字节)
            System.out.println("上传"+filename+"到("+realPath+")完毕，共计"+size+"字节,上传后文件名为'"+newname+"'。");
        }
        os.close();is.close();//资源释放(原则：先开后关，后开先关)
        return "welcome";//
    }
    private String doFilename(String filename){
         String extension = FilenameUtils.getExtension(filename);
        String uuid = UUID.randomUUID().toString();

        return uuid+"."+extension;
    }
    @RequestMapping(value = "/download.do/{filename}")
    public void download(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition","attachment:filename="+doFilename2(request,filename));
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("下载路径realPath="+realPath);
        InputStream is = new FileInputStream(new File(realPath,filename));
        OutputStream  os = response.getOutputStream();
        int size = IOUtils.copy(is,os);
        os.close();is.close();
        if (size > 0){
            System.out.println("下载成功");
        }
        
        
    }

    public String doFilename2(HttpServletRequest request, String filename){

        try {
            String userAgent = request.getHeader("User-Agent");
        if (userAgent.toUpperCase().indexOf("FIREFOX")>0){
            filename = "=?UTF-8?B?"+(new String(Base64.encodeBase64(filename.getBytes("utf-8"))));

        }else {
            filename = URLEncoder.encode(filename,"utf-8");
        }
            System.out.println("下载文件名"+filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return filename;
    }
}