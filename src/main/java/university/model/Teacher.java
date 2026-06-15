package university.model;

import lombok.*;
import university.IPrintable;

/**
 * Содержит основную информацию об учителе: имя, возраст, предмет, который преподает
 */

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Teacher implements IPrintable {

    private String name;
    private int age;
    private String subject;

    public Teacher(String name, int age, String subject) {
        this.name = name;
        this.age = age;
        this.subject = subject;
    }

    @Override
    public void printInfo() {
        System.out.printf("""
                Преподаватель: %s,
                Возраст: %d,
                Предмет: %s%n
                """, name, age, subject);
    }

}
