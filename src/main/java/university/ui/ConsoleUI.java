package university.ui;

import lombok.extern.slf4j.Slf4j;
import university.model.Group;
import university.model.Teacher;
import university.service.UniversityManager;
import university.strategy.SortByAgeStrategy;

import java.util.Scanner;

/**
 * Консольный пользовательский интерфейс.
 *
 * Отвечает только за взаимодействие пользователя
 * с системой через меню.
 */

@Slf4j
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
        log.info("Создан студент: {}", studentId);
    }

    private void createTeacher() {

        System.out.print("Имя: ");
        String name = scanner.nextLine();

        System.out.print("Возраст: ");
        int age = readInt();

        System.out.print("Предмет: ");
        String subject = scanner.nextLine();

        manager.createTeacher(name, age, subject);
        log.info("Создан преподаватель {}", name);
    }

    private void createGroup() {

        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        manager.createGroup(groupName);
        log.info("Создана группа {}", groupName);
    }

    private void createAuditorium() {

        System.out.print("Номер кабинета: ");
        int cabinetNumber = readInt();

        System.out.print("Вместимость: ");
        int capacity = readInt();

        manager.createAuditorium(cabinetNumber, capacity);
        log.info("Созданa аудитория номер {}", cabinetNumber);
    }

    private void createSubject() {

        System.out.print("Название предмета: ");
        String subjectName = scanner.nextLine();

        System.out.print("Имя преподавателя: ");
        String teacherName = scanner.nextLine();

        System.out.print("Номер аудитории: ");
        int cabinetNumber = readInt();

        boolean created = manager.createSubject(subjectName, teacherName, cabinetNumber);

        if (created) {
            System.out.println("Предмет создан");
        } else {
            System.out.println("Преподаватель или аудитория не найдены");
        }
    }

    private void addStudentToGroup() {

        System.out.print("Номер студенческого билета: ");
        String studentId = scanner.nextLine();

        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        boolean added = manager.addStudentToGroup(studentId, groupName);

        if (added) {
            System.out.println("Студент добавлен в группу");
        } else {
            System.out.println("Студент или группа не найдены");
        }
    }

    private void addSubjectToGroup() {

        System.out.print("Название предмета: ");
        String subjectName = scanner.nextLine();

        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        boolean added = manager.addSubjectToGroup(subjectName, groupName);

        if (added) {
            System.out.println("Предмет добавлен в группу");
        } else {
            System.out.println("Предмет или группа не найдены");
        }
    }

    private void showStudentsInGroup() {

        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        Group group = manager.findGroupByName(groupName).orElse(null);

        if (group == null) {
            System.out.println("Группа не найдена");
            return;
        }

        manager.printStudentsInGroup(group);
    }

    private void showSubjectsInGroup() {

        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        Group group = manager.findGroupByName(groupName).orElse(null);

        if (group == null) {
            System.out.println("Группа не найдена");
            return;
        }

        manager.printSubjectsInGroup(group);
    }

    private void showTeacherSubjects() {

        System.out.print("Имя преподавателя: ");
        String teacherName = scanner.nextLine();

        Teacher teacher = manager.findTeacherByName(teacherName).orElse(null);

        if (teacher == null) {
            System.out.println("Преподаватель не найден");
            return;
        }

        manager.printTeacherSubjects(teacher);
    }

    private void sortStudentsByAge() {

        System.out.print("Название группы: ");
        String groupName = scanner.nextLine();

        Group group = manager.findGroupByName(groupName).orElse(null);

        if (group == null) {
            System.out.println("Группа не найдена");
            return;
        }

        group.setSortStrategy(new SortByAgeStrategy());
        group.sortStudents();

        System.out.println("\nСтуденты группы по возрасту:");

        manager.printStudentsInGroup(group);
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                log.warn("Пользователь ввел некорректное число: {}", e.getMessage());
                System.out.println("Ошибка: введите число");
            }
        }
    }
}