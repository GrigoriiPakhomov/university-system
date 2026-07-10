package university.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeacherTest {

    @Test
    @DisplayName("Проверка создания преподавателя")
    void testCreateTeacher() {

        Teacher teacher = new Teacher("Ludmila", 50, "History");

        assertThat(teacher.getName()).isEqualTo("Ludmila");
        assertThat(teacher.getAge()).isEqualTo(50);
        assertThat(teacher.getSubject()).isEqualTo("History");
    }
}