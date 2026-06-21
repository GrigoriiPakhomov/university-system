package university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import university.IPrintable;
import university.strategy.StudentSortStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Содержит основную информацию о группе: название группы, список студентов
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Group implements IPrintable {

    private String groupName;
    private List<Student> students;
    private List<Subject> subjects;
    @JsonIgnore
    private StudentSortStrategy sortStrategy;

    public Group(String groupName) {
        this.groupName = groupName;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setGroup(this);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void setSortStrategy(StudentSortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sortStudents() {
        if (sortStrategy!=null) {
            sortStrategy.sort(students);
        }
    }

    @Override
    public void printInfo() {
        log.info("""
                Группа: {},
                Список студентов: {},
                Количество предметов: {},              
                """, groupName, students.size(), subjects.size());
    }

}
