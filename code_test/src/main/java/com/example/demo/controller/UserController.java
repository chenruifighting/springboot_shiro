package com.example.demo.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author 陈睿
 * @date 2020-03-19 10:02
 */
@SessionAttributes("code")
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/code")
    @ResponseBody  //？？？？为什么要加这个，不然控制台报错
    public void code(String telephone, Model model){
        String code="";
        for(int i = 1 ; i <= 6;i++){
            code+=(int)Math.floor(Math.random()*10);
        }
        model.addAttribute("code",code);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FmNSpNLY6gtmxBU2GpF", "2OYsoj8WTRqDOhM2wKpCrdENlpCiFE");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId","cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",telephone);
        request.putQueryParameter("SignName","图书管理系统");
        request.putQueryParameter("TemplateCode","SMS_186395978");
        request.putQueryParameter("TemplateParam","{'code':"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            //System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/doCode")
    public String doCode(String code, ModelMap modelMap){
        String c=modelMap.get("code").toString();
        if (c.equals(code)){
            return "success";
        }else {
            return "fail";
        }
    }
}
