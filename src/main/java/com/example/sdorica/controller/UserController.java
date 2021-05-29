package com.example.sdorica.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(HttpServletRequest req){
        JSONObject object = new JSONObject();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(!username.equals("asd")){
            object.put("code", 400);
            object.put("msg","用户不存在");
        }else if(!password.equals("123")){
            object.put("code", 502);
            object.put("msg","密码错误");
        }else{
            object.put("code", 200);
            object.put("msg","验证通过");
        }
        return object;
    }
}
