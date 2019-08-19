package test;

import demo.model.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudent {

    @Test
    public void testObjAttr() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) applicationContext.getBean("stu");

        System.out.println(student);
    }

    @Test
    public void testListAttr() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext2.xml");
        Student student = (Student) applicationContext.getBean("student");

//        System.out.println(student.getLessons());
//        System.out.println(student.getGirlFriends());
//        System.out.println(student.getLessonScores());
        System.out.println(student.getMysqlInfos());
    }
}
