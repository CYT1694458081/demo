package cn.controller;

import cn.domain.Res;
import cn.service.LogService;
import cn.service.UserService;
import cn.user.Log;
import cn.user.User;
import com.mysql.jdbc.PreparedStatement;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @RequestMapping("/public/showallU")
    public Res show(int useId){
        String Do ="查找所有信息";
        logService.createLog(Do, useId);
        List<User> list=userService.getAll();
        return new Res(Res.SUCCESS,"查找成功",list);
    }
    @RequestMapping("/public/delU")
    public Res del(int id,int useId){
        String Do ="删除ID:"+id;
        if(userService.delUser(id)){
            Do=Do+"成功";
            logService.createLog(Do, useId);
            return new Res(Res.SUCCESS,"删除成功！",null);
        }else {
            Do=Do+"失败";
            logService.createLog(Do, useId);
            return new Res(Res.ERROR,"删除失败！",null);
        }
    }
    @RequestMapping("/public/addU/{id}")
    public Res add(User user,@PathVariable("id") int useId){
        String Do ="注册用户:"+user.getUserName();
        System.out.println(user);
        if(userService.getUserById(user.getId())!=null){
            Do=Do+"失败";
            logService.createLog(Do, useId);
            return new Res(Res.FAILURE,"用户已经存在！",null);
        }
        if(userService.addUser(user)){
            Do=Do+"成功";
            logService.createLog(Do, useId);
            return new Res(Res.SUCCESS,"添加成功！",null);
        }else {
            Do=Do+"失败";
            logService.createLog(Do, useId);
            return new Res(Res.ERROR,"添加失败！",null);
        }
    }
    @RequestMapping("/public/upU/{id}")
    public Res up(User user,@PathVariable("id") int userId){
        String Do ="修改用户信息";
        System.out.println(user+"----"+userId);
        if(userService.upUser(user)){
            Do=Do+"成功";
            logService.createLog(Do, userId);
            return new Res(Res.SUCCESS,"修改成功！",null);
        }
        else {
            Do=Do+"失败";
            logService.createLog(Do, userId);
            return new Res(Res.ERROR,"修改失败！",null);
        }
    }
//@RequestMapping("/public/upU")
//public Res up(User user,int userId){
//    String Do ="修改用户信息";
//    System.out.println(user);
//    if(userService.upUser(user)){
//        Do=Do+"成功";
//        //logService.createLog(Do, useId);
//        return new Res(Res.SUCCESS,"修改成功！",null);
//    }
//    else {
//        Do=Do+"失败";
//        //logService.createLog(Do, useId);
//        return new Res(Res.ERROR,"修改失败！",null);
//    }
//}
    @RequestMapping("/public/selectU")
    public Res select(@RequestParam("searchParam") String FuzzyId,int useId){
        String Do ="查找信息："+FuzzyId;
        logService.createLog(Do, useId);
        List<User> list=userService.selectUser("%"+FuzzyId+"%");
        return new Res(Res.SUCCESS,"查找成功",list);
    }


}
