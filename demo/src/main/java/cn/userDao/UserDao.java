package cn.userDao;

import cn.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDao {
       User getUserById(int id);
       List<User> getAllUser();
       List<User> selectUser(String FuzzyId);
       boolean delUserById(int id);
       boolean upUser(User user);
       boolean addUser(User user);
}
