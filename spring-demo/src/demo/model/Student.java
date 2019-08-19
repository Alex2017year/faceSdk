package demo.model;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Data
public class Student {

    private String name;
    private int age;
    private int score;
    private List<String> lessons;
    private Set<String> girlFriends;
    private Map<String, Integer> lessonScores;
    private Properties mysqlInfos; // 数据库连接信息
    private String[] classmates;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }

}
