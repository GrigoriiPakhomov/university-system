package university.factory;

import university.model.*;

/**
 * Фабрика для создания объектов системы.
 * <p>
 * Реализует паттерн Factory Method.
 */

public final class UniversityFactory {

    private UniversityFactory() {
    }

    public static Student createStudent(String name, int age, String studentId) {
        return new Student(name, age, studentId);
    }

    public static Teacher createTeacher(String name, int age, String subject) {
        return new Teacher(name, age, subject);
    }

    public static Auditorium createAuditorium(int cabinetNumber, int capacity) {
        return new Auditorium(cabinetNumber, capacity);
    }

    public static Group createGroup(String groupName) {
        return new Group(groupName);
    }

    public static Subject createSubject(String name, Teacher teacher, Auditorium auditorium) {
        return new Subject(name, teacher, auditorium);
    }
}