package university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import university.IPrintable;

/**
 * Содержит основную информацию о студенте: имя, возраст, номер студенческого билета
 */

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "studentId")
public class Student implements IPrintable {

    private String name;
    private int age;
    private String studentId;
    @JsonIgnore
    private Group group;

    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    @Override
    public void printInfo() {
        System.out.printf("""
                Студент: %s,
                Возраст: %d,
                ID: %s%n
                """, name, age, studentId);
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
