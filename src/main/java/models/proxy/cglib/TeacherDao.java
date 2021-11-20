package models.proxy.cglib;

public class TeacherDao {
    public void teach(String subject) {
        System.out.println(subject + "老师授课中...(CGLIB无接口实现)");
    }
}
