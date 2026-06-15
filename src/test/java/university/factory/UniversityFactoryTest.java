package university.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import university.model.Student;

import static org.assertj.core.api.Assertions.assertThat;

class UniversityFactoryTest {

    @Test
    @DisplayName("Проверка создания студента через фабрику")
    void testCreateStudent() {
        Student student = UniversityFactory.createStudent("Grigorii", 30, "999");

        assertThat(student.getName()).isEqualTo("Grigorii");
        assertThat(student.getStudentId()).isEqualTo("999");
    }
}