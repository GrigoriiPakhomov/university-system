package university.repository;

import lombok.Getter;
import university.model.Auditorium;
import university.model.Group;
import university.model.Student;
import university.model.Subject;
import university.model.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранилище данных приложения.
 * <p>
 * Содержит коллекции студентов,
 * преподавателей, групп, предметов
 * и аудиторий.
 * <p>
 * Используется как единая точка
 * хранения данных системы.
 */

@Getter
public class UniversityRepository {

    private List<Student> students;
    private List<Teacher> teachers;
    private List<Group> groups;
    private List<Subject> subjects;
    private List<Auditorium> auditoriums;

    public UniversityRepository() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.auditoriums = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void addAuditorium(Auditorium auditorium) {
        auditoriums.add(auditorium);
    }
}