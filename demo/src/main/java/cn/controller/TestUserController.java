package cn.controller;

import cn.service.LogService;
import cn.service.UserService;
import cn.user.Log;
import cn.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/testBoot")
public class TestUserController {
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @RequestMapping("getUser/{id}")
    public  String getId(@PathVariable int id){
          return userService.getUserById(id).toString();
    }
    @RequestMapping("getall")
    public List<Log> getall(){
        return logService.getAll();
    }
    @RequestMapping("selectId/{FuzzyId}")
    public List<User> selectId(@PathVariable String FuzzyId){
        return userService.selectUser("%"+FuzzyId+"%");
    }





}
