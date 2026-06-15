package university.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectTest {

    @Test
    @DisplayName("Проверка создания предмета")
    void testCreateSubject() {
        Teacher teacher = new Teacher("Ludmila", 50, "History");
        Auditorium auditorium = new Auditorium(208, 30);

        Subject subject = new Subject("History", teacher, auditorium);

        assertThat(subject.getName()).isEqualTo("History");
        assertThat(subject.getTeacher()).isSameAs(teacher);
        assertThat(subject.getAuditorium()).isSameAs(auditorium);
    }
}