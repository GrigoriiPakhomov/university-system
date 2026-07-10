package university.strategy;

import university.model.Student;

import java.util.Comparator;
import java.util.List;

/**
 * Стратегия сортировки студентов
 * по имени.
 */

public class SortByNameStrategy implements StudentSortStrategy {

    @Override
    public void sort(List<Student> students) {
        students.sort(Comparator.comparing(Student::getName));
    }
}