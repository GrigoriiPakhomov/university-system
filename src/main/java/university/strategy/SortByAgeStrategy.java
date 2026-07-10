package university.strategy;

import university.model.Student;

import java.util.Comparator;
import java.util.List;

/**
 * Стратегия сортировки студентов
 * по возрасту.
 */

public class SortByAgeStrategy implements StudentSortStrategy {

    @Override
    public void sort(List<Student> students) {
        students.sort(Comparator.comparingInt(Student::getAge));
    }
}