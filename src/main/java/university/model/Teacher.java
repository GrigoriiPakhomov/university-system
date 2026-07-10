package university.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import university.IPrintable;

/**
 * Содержит основную информацию об учителе: имя, возраст, предмет, который преподает
 */

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Slf4j
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
        log.info("""
                Преподаватель: {},
                Возраст: {},
                Предмет: {}
                """, name, age, subject);
    }
}
