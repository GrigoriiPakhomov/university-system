package university.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import university.model.Student;
import university.model.Group;

import static org.assertj.core.api.Assertions.assertThat;

class UniversityManagerTest {

    @Test
    @DisplayName("getInstance должен возвращать один и тот же объект")
    void testGetInstance() {
        UniversityManager manager1 = UniversityManager.getInstance();
        UniversityManager manager2 = UniversityManager.getInstance();

        assertThat(manager1).isSameAs(manager2);
    }

    @Test
    @DisplayName("Создание студента должно добавлять его в репозиторий")
    void testCreateStudent() {

        UniversityManager manager = UniversityManager.getInstance();
        Student student = manager.createStudent("Grigorii", 30, "999"
        );

        assertThat(manager.getRepository().getStudents()).contains(student);
    }

    @Test
    @DisplayName("Поиск группы по названию")
    void testFindGroupByName() {
        UniversityManager manager = UniversityManager.getInstance();
        Group group = manager.createGroup("FKU-07-1");
        Group foundGroup = manager.findGroupByName("FKU-07-1");

        assertThat(foundGroup).isSameAs(group);
    }

    @Test
    @DisplayName("Добавление студента в группу через менеджер")
    void testAddStudentToGroup() {
        UniversityManager manager = UniversityManager.getInstance();
        Student student = manager.createStudent("Ivan", 20, "1");
        Group group = manager.createGroup("FKU");

        manager.addStudentToGroup(student, group);

        assertThat(group.getStudents()).contains(student);
        assertThat(student.getGroup()).isSameAs(group);
    }
}