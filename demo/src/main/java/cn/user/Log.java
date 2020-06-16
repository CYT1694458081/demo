package cn.user;

import java.sql.Date;

public class Log {
    int id;
    int userId;
    Date date;
    String todo;

    public Log(int id,int userId, String todo,Date date) {
        this.id = id;
        this.userId=userId;
        this.date = date;
        this.todo = todo;
    }
    public Log(int userId, String todo,Date date) {
        this.userId=userId;
        this.date = date;
        this.todo = todo;
    }
    public Log(){
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    @Override
    public String toString() {
        return "LogDao{" +
                "id=" + id +
                ", date=" + date +
                ", todo='" + todo + '\'' +
                '}';
    }
}
