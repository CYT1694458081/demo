package cn.controller;

import cn.domain.Res;
import cn.service.UserService;
import cn.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class Login {
    @Autowired
    UserService userService;
    private User useU;
    @PostMapping("/public/login")
//    @RequestMapping("/public/login")
    public Res userlogin(@RequestParam("userid") int id,@RequestParam("password") String passWord){
        useU=userService.getUserById(id);
        System.out.println(id+"    "+passWord);
        if(useU!=null){
            if(useU.getPassWord().equals(passWord)){
                return new Res(Res.SUCCESS,"登录成功",useU);
            }else {
                return new Res(Res.ERROR,"密码错误",null);
            }
        } else return new Res(Res.ERROR,"用户不存在",null);
    }

    public User getUseU(){
        return this.useU;
    }

}
