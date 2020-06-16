package cn.service;

import cn.user.Log;
import cn.userDao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService {
    @Autowired
    LogDao logDao;
    public List<Log> getAll(){
        return logDao.getAll();
    }
    public boolean del(int id){
        return logDao.del(id);
    }
    public List<Log> getById(int id){
        return logDao.getById(id);
    }
    public boolean add(Log log){
        return logDao.add(log);
    }
    public List<Log> select(String fuzzy){
        return logDao.select(fuzzy);
    }
    public void createLog(String Do, int id){
        Date date =new Date();
        Log log = new Log(id,Do,new java.sql.Date(date.getTime()));
        this.add(log);
    }
}
