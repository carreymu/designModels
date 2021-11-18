package models.proxy.dynamicProxy;

public class TeacherDao implements ITeacherDao {
    @Override
    public void teach(String subject) {
        System.out.println(subject + "老师授课中...");
    }
}
