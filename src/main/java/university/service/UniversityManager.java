package university.service;

import lombok.Getter;
import java.util.Optional;
import university.factory.UniversityFactory;
import university.model.Auditorium;
import university.model.Group;
import university.model.Student;
import university.model.Subject;
import university.model.Teacher;
import university.repository.UniversityRepository;
import university.storage.JsonStorage;

/**
 * Центральный класс системы.
 * <p>
 * Реализует паттерн Singleton.
 * Отвечает за управление сущностями университета
 * и взаимодействие между Repository и Factory.
 */

@Getter
public class UniversityManager {

    private static UniversityManager instance;
    private final JsonStorage storage;
    private final UniversityRepository repository;

    public static UniversityManager getInstance() {
        if (instance==null) {
            instance = new UniversityManager();
        }
        return instance;
    }

    private UniversityManager() {
        this.repository = new UniversityRepository();
        this.storage = new JsonStorage();
    }

    public Student createStudent(String name, int age, String studentId) {
        Student student = UniversityFactory.createStudent(name, age, studentId);
        repository.addStudent(student);
        return student;
    }

    public Teacher createTeacher(String name, int age, String subject) {
        Teacher teacher = UniversityFactory.createTeacher(name, age, subject);
        repository.addTeacher(teacher);
        return teacher;
    }

    public Group createGroup(String groupName) {
        Group group = UniversityFactory.createGroup(groupName);
        repository.addGroup(group);
        return group;
    }

    public Auditorium createAuditorium(int cabinetNumber, int capacity) {
        Auditorium auditorium = UniversityFactory.createAuditorium(cabinetNumber, capacity);
        repository.addAuditorium(auditorium);
        return auditorium;
    }

    public Subject createSubject(String name, Teacher teacher, Auditorium auditorium) {
        Subject subject = UniversityFactory.createSubject(name, teacher, auditorium);
        repository.addSubject(subject);
        return subject;
    }

    public void addStudentToGroup(Student student, Group group) {
        group.addStudent(student);
    }

    public void addSubjectToGroup(Subject subject, Group group) {
        group.addSubject(subject);
    }

    public Optional<Group> findGroupByName(String groupName) {

        for (Group group : repository.getGroups()) {
            if (group.getGroupName().equalsIgnoreCase(groupName)) {
                return Optional.of(group);
            }
        }
        return Optional.empty();
    }

    public Optional<Teacher> findTeacherByName(String teacherName) {

        for (Teacher teacher : repository.getTeachers()) {
            if (teacher.getName().equalsIgnoreCase(teacherName)) {
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }

    public void printStudentsInGroup(Group group) {
        for (Student student : group.getStudents()) {
            student.printInfo();
        }
    }

    public void printSubjectsInGroup(Group group) {
        for (Subject subject : group.getSubjects()) {
            subject.printInfo();
        }
    }

    public void printTeacherSubjects(Teacher teacher) {
        for (Subject subject : repository.getSubjects()) {
            if (subject.getTeacher().equals(teacher)) {
                subject.printInfo();
            }
        }
    }

    public void saveData() {
        storage.save(repository);
    }

    public void loadData() {
        UniversityRepository loadedRepository = storage.load();
        repository.getStudents().addAll(loadedRepository.getStudents());
        repository.getTeachers().addAll(loadedRepository.getTeachers());
        repository.getGroups().addAll(loadedRepository.getGroups());
        repository.getSubjects().addAll(loadedRepository.getSubjects());
        repository.getAuditoriums().addAll(loadedRepository.getAuditoriums());
    }
}