package university.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import university.IPrintable;

/**
 * Содержит основную информацию о предмете: название предмета, преподаватель предмета, аудитория предмета
 */

@Getter
@NoArgsConstructor
public class Subject implements IPrintable {
    private String name;
    private Teacher teacher;
    private Auditorium auditorium;

    public Subject(String name, Teacher teacher, Auditorium auditorium){
        this.name = name;
        this.teacher = teacher;
        this.auditorium = auditorium;
    }

    @Override
    public void printInfo() {
        System.out.printf("""
                Название предмета: %s,
                Преподаватель: %s,
                Аудитория: %s%n
                """, name, teacher, auditorium);
    }
}
