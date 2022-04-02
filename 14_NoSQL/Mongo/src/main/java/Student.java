import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Student {
    private String fullName;
    private int age;
    private List<String> courses;

    public Student() {
    }

    public Student(String[] strings){
        this.fullName = strings[0];
        this.age = Integer.parseInt(strings[1]);
        this.courses = Arrays.stream(strings[2].split(",")).collect(Collectors.toList());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student {" + "fullName=" + fullName + ", age=" + age + ", courses=" + courses + '}';
    }
}
