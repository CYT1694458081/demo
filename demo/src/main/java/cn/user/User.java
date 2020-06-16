package cn.user;

public class User {
    private  int id;
    private  String userName;
    private  String passWord;
    private  String className;
    private  String job;
    private  float money;


    public User(int id, String username, String passWord, String className, String job, float money) {
        this.id = id;
        this.userName = username;
        this.passWord = passWord;
        this.className = className;
        this.job = job;
        this.money = money;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", className='" + className + '\'' +
                ", job='" + job + '\'' +
                ", money=" + money +
                '}';
    }
}
