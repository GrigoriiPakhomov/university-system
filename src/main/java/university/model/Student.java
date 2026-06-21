package university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import university.IPrintable;

/**
 * Содержит основную информацию о студенте: имя, возраст, номер студенческого билета
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "studentId")
@Slf4j
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
        log.info("""
                Студент: {},
                Возраст: {},
                ID: {}
                """, name, age, studentId);
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
