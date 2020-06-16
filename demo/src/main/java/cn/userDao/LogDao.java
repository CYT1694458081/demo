package cn.userDao;
import cn.user.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogDao {
        List<Log> getAll();
        List<Log> getById(int id);
        List<Log> select(String fuzzy);
        boolean del(int id);
        boolean add(Log log);


}
