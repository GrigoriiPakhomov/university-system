package university.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import university.strategy.SortByAgeStrategy;

import static org.assertj.core.api.Assertions.assertThat;

class GroupTest {

    @Test
    @DisplayName("Проверка добавления студента в группу")
    void testAddStudent() {
        // Arrange
        Group group = new Group("FKU-07-1");
        Student student = new Student("Grigorii", 35, "999");

        // Act
        group.addStudent(student);

        // Assert
        assertThat(group.getStudents()).contains(student);
        assertThat(student.getGroup()).isSameAs(group);
    }

    @Test
    @DisplayName("Проверка добавления предмета в группу")
    void testAddSubject() {
        Group group = new Group("FKU 07-1");
        Teacher teacher = new Teacher("Ludmila", 50, "History");
        Auditorium auditorium = new Auditorium(208, 30);
        Subject subject = new Subject("History", teacher, auditorium);

        group.addSubject(subject);

        assertThat(group.getSubjects()).contains(subject);
    }

    @Test
    @DisplayName("Проверка сортировки студентов по возрасту")
    void testSortStudentsByAge() {
        Group group = new Group("FKU-07-1");
        Student student1 = new Student("Ivan", 25, "1");
        Student student2 = new Student("Petr", 20, "2");

        group.addStudent(student1);
        group.addStudent(student2);

        group.setSortStrategy(new SortByAgeStrategy());
        group.sortStudents();

        assertThat(group.getStudents()).containsExactly(student2, student1);
    }
}