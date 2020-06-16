package cn.controller;

import cn.domain.Res;
import cn.service.LogService;
import cn.user.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserLogController {
    @Autowired
    private LogService logService;
    @RequestMapping("/public/showallL")
    public Res show(){
        List<Log> list=logService.getAll();
        return new Res(Res.SUCCESS,"查找成功",list);
    }
    @RequestMapping("/public/delL")
    public Res del(int id){
        if(logService.del(id)){
            return new Res(Res.SUCCESS,"删除成功！",null);
        }else {
            return new Res(Res.ERROR,"删除失败！",null);
        }
    }



//    public Res add(Log log){
//        System.out.println(log);
//        if(logService.getById(log.getId())!=null){
//            return new Res(Res.FAILURE,"用户已经存在！",null);
//        }
//        if(logService.add(log)){
//            return new Res(Res.SUCCESS,"添加成功！",null);
//        }else return new Res(Res.ERROR,"添加失败！",null);
//    }

    @RequestMapping("/public/selectL")
    public Res select(@RequestParam("searchParam") String fuzzy){
        List<Log> list=logService.select("%"+fuzzy+"%");
        return new Res(Res.SUCCESS,"查找成功",list);
    }
}
