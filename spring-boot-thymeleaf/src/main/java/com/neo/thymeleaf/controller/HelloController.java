package com.neo.thymeleaf.controller;

import com.neo.thymeleaf.Utils.GsonUtil;
import com.neo.thymeleaf.Utils.HttpRequestor;
import com.neo.thymeleaf.Utils.NetUtil;
import com.neo.thymeleaf.dao.InfoMapper;
import com.neo.thymeleaf.entity.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Controller
public class HelloController {

    //将数据库接口注入进来
    @Autowired
    private InfoMapper infoMapper;

    //跳转到index.html的控制器
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        name = "jason";
        model.addAttribute("name", name);
        return "index";
    }

    //用户登陆的控制器，包括获取用户的access_token
    @RequestMapping(value="/test" ,method = RequestMethod.GET)
    public String login(Model model, @RequestParam(value="code") String code , HttpServletRequest req){
        //获取认证服务器里面的code
        System.out.println(code);
        model.addAttribute("name", code);
        Map<String,Object> dataMap = new HashMap<>();
        //将code传到认证服务器，并且获取对应的access_token
        String jsonstr = NetUtil.POST("https://gitee.com/oauth/token?grant_type=authorization_code&code="+code+"&client_id=947a1363deb6b3fad43e05cd8061c5ad977c2c9ac2c303e821e125fb72861557&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ftest&client_secret=5c24a4620f972087219a6606a9a9592862d56395144902e23ca336967cf5c0d4",dataMap);
        System.out.println(jsonstr);
        //解析jsonstr并且获取access_token,并且利用GSON解析json分装成对象
        Access_token access_token = GsonUtil.parseJsonWithGson(jsonstr, Access_token.class);
        //并且将access_token放到session里面
        HttpSession session = req.getSession();
        session.setAttribute("access_token",access_token.getAccess_token());
        infoMapper.insertByParameter("认证成功",new Date());
        System.out.println(access_token);
        return "login_success";
    }

    //跳转到选择项目的控制器，包括查出用户的资料
    @RequestMapping(value="/choosexm" ,method = RequestMethod.GET)
    public String query_xm( HttpServletRequest req){
        String access_token = (String) req.getSession().getAttribute("access_token");
        //发送请求将access_token发送至认证服务器，获取User信息
        String jsonstr = NetUtil.GET("http://gitee.com/api/v5/user?access_token="+access_token);
         System.out.println(jsonstr);
        //将从后台获取的数据解析成Uer对象
         User user = GsonUtil.parseJsonWithGson(jsonstr, User.class);
         System.out.println(user);
         HttpSession session = req.getSession();
         //将login放到后台里面
         String login = user.getLogin();
         session.setAttribute("login",user.getLogin());
         //想后台数据查找用户的所有项目信息
         String jsonstr1 = NetUtil.GET("http://gitee.com/api/v5/users/"+login+"/repos?access_token="+access_token+"&type=all&sort=full_name&page=1&per_page=20");
         infoMapper.insertByParameter("查找所有项目消息",new Date());
        //将信息解析成项目对象
        System.out.println(jsonstr1);
         List<Xm> xms = new ArrayList<>();
         xms = GsonUtil.jsonToArrayList(jsonstr1, Xm.class);
         System.out.println(xms);
        //将项目信息发送到前台中，让用户选择
         req.setAttribute("xms",xms);
        return "xms";
    }

    //跳转到新建文件的控制器
    @RequestMapping(value ="/newfile",method = RequestMethod.POST)
    public String newfile(@RequestParam(value="xm") String xm ,@RequestParam(value="path") String path
    ,@RequestParam(value = "content") String content ,@RequestParam(value = "message")String message,HttpServletRequest req
    ,HttpServletResponse response) throws Exception {
        //将前台获取的项目名字，以及新建文件的相关信息
        System.out.println(xm+path+content+message);
        //将文件内容进行去前后空格
        content = content.trim();
         message = message.trim();
        //将文件内容进行Base64编码
        byte[] encodeBase64 = new byte[0];
        try {
            encodeBase64 = Base64.encodeBase64(content.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        content = new String(encodeBase64);
        System.out.println(content);
        //从session中获取access_token 和login
        String access_token = (String) req.getSession().getAttribute("access_token");
        String login = (String) req.getSession().getAttribute("login");
        //发送POST请求，新建文件
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("access_token",access_token);
        dataMap.put("owner",login);
        dataMap.put("repo",xm);
        dataMap.put("path",path);
        dataMap.put("content",content);
        dataMap.put("message",message);
        System.out.println(access_token+login+xm+path+content+message);
        String jasonstr  = new HttpRequestor().doPost("https://gitee.com/api/v5/repos/"+login+"/"+xm+"/contents/"+path,dataMap);
        System.out.println(jasonstr);
        infoMapper.insertByParameter("新建文件成功",new Date());
        //重定向到码云新添加的文件的页面
        response.sendRedirect("https://gitee.com/"+login+"/"+xm+"/blob/master/"+path);
        //直接请求道新建文件的页面
        //NetUtil.GET("https://gitee.com/"+login+"/"+xm+"/blob/master/"+path);
        return null;
    }

    //让用户选择操作文件的控制器
    @RequestMapping(value = "/queryfile")
    public String choosexm(HttpServletRequest req){
        String login  = (String) req.getSession().getAttribute("login");
        String access_token = (String) req.getSession().getAttribute("access_token");
        //发送GET请求，获取所有的项目，让用户选择
        String jsonstr1 = NetUtil.GET("http://gitee.com/api/v5/users/"+login+"/repos?access_token="+access_token+"&type=all&sort=full_name&page=1&per_page=20");
        infoMapper.insertByParameter("获取所有项目信息",new Date());
        //将信息解析成项目对象
        System.out.println(jsonstr1);
        List<Xm> xms = new ArrayList<>();
        xms = GsonUtil.jsonToArrayList(jsonstr1, Xm.class);
        System.out.println(xms);
        //将项目信息发送到前台中，让用户选择
        req.setAttribute("xms",xms);
        return "choosexm";
    }


     //查询所有文件，然后分装成对象，展示在前台页面
    @RequestMapping(value ="/queryallfiles",method = RequestMethod.POST)
    public String queryAllfiles(HttpServletRequest req,@RequestParam(value = "xm")String xm){
        //发送GET请求，获取所有文件
        String login = (String) req.getSession().getAttribute("login");
        String access_token = (String) req.getSession().getAttribute("access_token");
        String jsonstr =  NetUtil.GET("http://gitee.com/api/v5/repos/"+login+"/"+xm+"/git/trees/master?access_token="+access_token);
        infoMapper.insertByParameter("查找所有项目信息",new Date());
        //将获取的json数据进行解析
        System.out.println(jsonstr);
        File file = GsonUtil.parseJsonWithGson(jsonstr, File.class);
        for(int i = 0;i<file.getTree().size();i++){
            file.getTree().get(i).setXm(xm);
        }
        System.out.println(file);
        //将解析的结果集发送到前台页面，进行展示
        req.setAttribute("file",file);
        return "filelist";
    }

    //删除文件的控制器
    @RequestMapping(value = "deletefile",method = RequestMethod.GET)
    public  String deletefile(HttpServletRequest req,@RequestParam("path")String path,
                              @RequestParam("xm") String xm,@RequestParam("sha") String sha,Model model){
        //从前台获取参数
        String access_token = (String) req.getSession().getAttribute("access_token");
        String login = (String) req.getSession().getAttribute("login");
        //发送DELETE请求，删除文件
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("access_token",access_token);
        dataMap.put("sha",sha);
        dataMap.put("owner",login);
        dataMap.put("repo",xm);
        dataMap.put("path",path);
        dataMap.put("message","hello");
        String jasonstr = NetUtil.DELETE("https://gitee.com/api/v5/repos/"+login+"/"+xm+"/contents/"+path+"?access_token="+access_token+"&sha="+sha+"&message=hello",dataMap);
        infoMapper.insertByParameter("删除文件成功",new Date());
        System.out.println(jasonstr);
        model.addAttribute("xm",xm);
        //重定向到选择项目的页面
        return "redirect:/queryfile";
    }

    //查看文件的控制器
    @RequestMapping(value = "detailfile")
    public String QueryFile(@RequestParam("xm")String xm,@RequestParam("sha") String sha,HttpServletRequest req){
        String access_token = (String) req.getSession().getAttribute("access_token");
        String login = (String) req.getSession().getAttribute("login");
        //发送GET请求，获取文件内容
        String jsonstr = NetUtil.GET("http://gitee.com/api/v5/repos/"+login+"/"+xm+"/git/blobs/"+sha+"?access_token="+access_token);
        infoMapper.insertByParameter("查找文件内容成功",new Date());
        //将json对象解析
        FileContent fileContent = GsonUtil.parseJsonWithGson(jsonstr, FileContent.class);
        System.out.println(fileContent);
        String content = fileContent.getContent();
        byte bytes [] = content.getBytes();
        bytes =  Base64.decodeBase64(bytes);
        content = new String(bytes);
        req.setAttribute("content",content);
        return "detail";
    }

    //编写更新文件内容的控制器
    @RequestMapping(value = "/updatefile")
    public String updatefile(@RequestParam("xm")String xm,@RequestParam("sha") String sha,HttpServletRequest req,
                             @RequestParam("path") String path){
        String access_token = (String) req.getSession().getAttribute("access_token");
        String login = (String) req.getSession().getAttribute("login");
        //发送GET请求，获取文件内容
        String jsonstr = NetUtil.GET("http://gitee.com/api/v5/repos/"+login+"/"+xm+"/git/blobs/"+sha+"?access_token="+access_token);
        //将json对象解析
        FileContent fileContent = GsonUtil.parseJsonWithGson(jsonstr, FileContent.class);
        System.out.println(fileContent);
        String content = fileContent.getContent();
        byte bytes [] = content.getBytes();
        bytes =  Base64.decodeBase64(bytes);
        content = new String(bytes);
        req.setAttribute("content",content);
        req.setAttribute("xm",xm);
        req.setAttribute("path",path);
        req.setAttribute("sha",sha);
        return "update";
    }

    //提交文件内容至后台服务器
    @RequestMapping(value = "submitfile")
    public String submitfile(@RequestParam("xm")String xm,@RequestParam("sha") String sha,HttpServletResponse resp,
                             HttpServletRequest req, @RequestParam("content") String content,@RequestParam("path") String path) throws Exception{
        String access_token = (String) req.getSession().getAttribute("access_token");
        String login = (String) req.getSession().getAttribute("login");
        //去掉前后空格
        content = content.trim();
        //将内容进行编码
        byte[] encodeBase64 = Base64.encodeBase64(content.getBytes("UTF-8"));
        content = new String(encodeBase64);
        //将参数分装
        NameValuePair[] params = new NameValuePair[7];
        params[0] = new NameValuePair("access_token",access_token);
        params[1] = new NameValuePair("owner",login);
        params[2] = new NameValuePair("path",path);
        params[3] = new NameValuePair("repo",xm);
        params[4] = new NameValuePair("sha",sha);
        params[5] = new NameValuePair("message","update");
        params[6] = new NameValuePair("content",content);
        //发送更新的请求
        String jasonstr =  NetUtil.PUT("https://gitee.com/api/v5/repos/"+login+"/"+xm+"/contents/"+path,params);
        infoMapper.insertByParameter("更新文件内容成功",new Date());
        System.out.println("https://gitee.com/api/v5/repos/"+login+"/"+xm+"/contents/"+path);
        System.out.println(jasonstr);
        //重定向到码云更新的页面上
        resp.sendRedirect("https://gitee.com/"+login+"/"+xm+"/blob/master/"+path);
        return "hello";
    }
}
