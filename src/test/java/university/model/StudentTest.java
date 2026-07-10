package university.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StudentTest {

    @Test
    @DisplayName("Проверка создания студента")
    void testCreateStudent(){
        Student student = new Student("Ivan", 30,"FK-06");

        assertThat(student.getName()).isEqualTo("Ivan");
        assertThat(student.getAge()).isEqualTo(30);
        assertThat(student.getStudentId()).isEqualTo("FK-06");
    }

    @Test
    @DisplayName("Проверка смены группы у сдудента")
    void testSetGroup(){
        Student student = new Student("Ivan", 30,"FK-06");
        Group group = new Group("FK-07");

        student.setGroup(group);

        assertThat(student.getGroup()).isSameAs(group);
    }
}
