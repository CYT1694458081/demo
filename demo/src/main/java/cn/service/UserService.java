package cn.service;

import cn.user.User;
import cn.userDao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserById(int id){
        return userDao.getUserById(id);
    }
    public List<User> getAll(){
        return userDao.getAllUser();
    }
    public List<User> selectUser(String FuzzyId){
        return userDao.selectUser(FuzzyId);
    }
    public boolean upUser(User user){
        return  userDao.upUser(user);
    }
    public boolean delUser(int id){
        return  userDao.delUserById(id);
    }
    public boolean addUser(User user){
        return  userDao.addUser(user);
    }
}
