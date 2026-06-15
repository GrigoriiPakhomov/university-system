package university.strategy;

import university.model.Student;

import java.util.List;

/**
 * Интерфейс стратегии сортировки студентов.
 * <p>
 * Позволяет изменять алгоритм сортировки
 * без изменения класса Group.
 */

public interface StudentSortStrategy {
    void sort(List<Student> students);
}