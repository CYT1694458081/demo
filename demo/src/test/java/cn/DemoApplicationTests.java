package cn;

import cn.domain.Res;
import cn.service.LogService;
import cn.service.UserService;
import cn.user.Log;
import cn.user.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class  DemoApplicationTests {

    @Autowired
    UserService userService;
@Autowired
    LogService logService;

    @Test
    public void getAll(){
        List<User> list=userService.getAll();
        for(User u: list){
            System.out.println(u);
        }
    }

@Test
    public void add(){
        User user =new User(66,"asd","123","1110","79879",4564);
    System.out.println(userService.addUser(user));
}

@Test
    public void lo(){
        int id=1;
        String passWord="456";
    User u=userService.getUserById(id);
    if(u!=null){
        if(u.getPassWord().equals(passWord)){
            System.out.println(new Res(Res.SUCCESS,"登录成功",u));
        }else {
            System.out.println(new Res(Res.ERROR,"密码错误",null));
        }
    } else System.out.println(new Res(Res.ERROR,"用户不存在",null));
}
@Test
    public void a(){
    List<Log> list=logService.getAll();
    for(Log u: list){
        System.out.println(u);
}
//    Log log=new Log(3,"王","删除用户",new Date(2019));
//    System.out.println(logService.add(log));
}
}
