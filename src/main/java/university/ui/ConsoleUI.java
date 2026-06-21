package university.ui;

import university.model.Teacher;
import university.model.Auditorium;
import university.model.Student;
import university.model.Subject;
import university.model.Group;
import university.service.UniversityManager;
import university.strategy.SortByAgeStrategy;

import java.util.Scanner;

/**
 * Консольный пользовательский интерфейс.
 * <p>
 * Отвечает за взаимодействие пользователя
 * с системой через меню.
 */

public class ConsoleUI {
    private final Scanner scanner;
    private final UniversityManager manager;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.manager = UniversityManager.getInstance();
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("""
                           
                    ===== Университетская система =====
                    1. Создать студента
                    2. Создать преподавателя
                    3. Создать группу
                    4. Создать аудиторию
                    5. Создать предмет
                    6. Добавить студента в группу
                    7. Добавить предмет в группу
                    8. Показать студентов группы
                    9. Показать предметы группы
                    10. Показать предметы преподавателя
                    11. Список студентов по возрасту
                    0. Выход
                    """);

            System.out.print("Выберите пункт меню: ");
            int choice = readInt();
            switch (choice) {
                case 1 -> createStudent();
                case 2 -> createTeacher();
                case 3 -> createGroup();
                case 4 -> createAuditorium();
                case 5 -> createSubject();
                case 6 -> addStudentToGroup();
                case 7 -> addSubjectToGroup();
                case 8 -> showStudentsInGroup();
                case 9 -> showSubjectsInGroup();
                case 10 -> showTeacherSubjects();
                case 11 -> sortStudentsByAge();
                case 0 -> {
                    manager.saveData();
                    running = false;
                    System.out.println("Завершение программы...");
                }
                default -> System.out.println("Неверный пункт меню");
            }
        }
    }

    private void createStudent() {

        System.out.print("Имя: ");
        String name = scanner.nextLine();

        System.out.print("Возраст: ");
        int age = readInt();

        System.out.print("Номер студенческого билета: ");
        String studentId = scanner.nextLine();

        manager.createStudent(name, age, studentId);
        System.out.println("Студент создан");
    }

    private void createTeacher() {
        System.out.print("Имя: ");
        String name = scanner.nextLine();

        System.out.print("Возраст: ");
        int age = readInt();

        System.out.print("Предмет: ");
        String subject = scanner.nextLine();

        manager.createTeacher(name, age, subject);
        System.out.println("Преподаватель создан");
    }

    private void createGroup() {
        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        manager.createGroup(groupName);
        System.out.println("Группа создана");
    }

    private void createAuditorium() {
        System.out.print("Номер кабинета: ");
        int cabinetNumber = readInt();

        System.out.print("Вместимость: ");
        int capacity = readInt();

        manager.createAuditorium(cabinetNumber, capacity);
        System.out.println("Аудитория создана");
    }

    private void createSubject() {
        System.out.print("Название предмета: ");
        String subjectName = scanner.nextLine();

        System.out.print("Имя преподавателя: ");
        String teacherName = scanner.nextLine();

        Teacher teacher = manager.findTeacherByName(teacherName);

        if (teacher==null) {
            System.out.println("Преподаватель не найден");
            return;
        }

        System.out.print("Номер аудитории: ");

        int cabinetNumber = readInt();
        Auditorium auditorium = null;

        for (Auditorium a : manager.getRepository().getAuditoriums()) {
            if (a.getCabinetNumber()==cabinetNumber) {
                auditorium = a;
                break;
            }
        }

        if (auditorium==null) {
            System.out.println("Аудитория не найдена");
            return;
        }

        manager.createSubject(subjectName, teacher, auditorium);
        System.out.println("Предмет создан");
    }

    private void addStudentToGroup() {
        System.out.print("Номер студенческого билета: ");
        String studentId = scanner.nextLine();

        Student student = null;

        for (Student s : manager.getRepository().getStudents()) {
            if (s.getStudentId().equals(studentId)) {
                student = s;
                break;
            }
        }

        if (student==null) {
            System.out.println("Студент не найден");
            return;
        }

        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        Group group = manager.findGroupByName(groupName);

        if (group==null) {
            System.out.println("Группа не найдена");
            return;
        }

        manager.addStudentToGroup(student, group);
        System.out.println("Студент добавлен в группу");
    }

    private void addSubjectToGroup() {
        System.out.print("Название предмета: ");
        String subjectName = scanner.nextLine();

        Subject subject = null;

        for (Subject s : manager.getRepository().getSubjects()) {
            if (s.getName().equalsIgnoreCase(subjectName)) {
                subject = s;
                break;
            }
        }

        if (subject==null) {
            System.out.println("Предмет не найден");
            return;
        }

        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        Group group = manager.findGroupByName(groupName);

        if (group==null) {
            System.out.println("Группа не найдена");
            return;
        }

        manager.addSubjectToGroup(subject, group);
        System.out.println("Предмет добавлен в группу");
    }

    private void showStudentsInGroup() {
        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        Group group = manager.findGroupByName(groupName);

        if (group==null) {
            System.out.println("Группа не найдена");
            return;
        }

        manager.printStudentsInGroup(group);
    }

    private void showSubjectsInGroup() {
        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        Group group = manager.findGroupByName(groupName);

        if (group==null) {
            System.out.println("Группа не найдена");
            return;
        }

        manager.printSubjectsInGroup(group);
    }

    private void showTeacherSubjects() {
        System.out.print("Имя преподавателя: ");
        String teacherName = scanner.nextLine();

        Teacher teacher = manager.findTeacherByName(teacherName);

        if (teacher==null) {
            System.out.println("Преподаватель не найден");
            return;
        }

        manager.printTeacherSubjects(teacher);
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }

    private void sortStudentsByAge() {
        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        Group group = manager.findGroupByName(groupName);

        if (group==null) {
            System.out.println("Группа не найдена");
            return;
        }

        group.setSortStrategy(new SortByAgeStrategy());
        group.sortStudents();
        System.out.println("\nСтуденты группы по возрасту:");
        manager.printStudentsInGroup(group);
    }
}
