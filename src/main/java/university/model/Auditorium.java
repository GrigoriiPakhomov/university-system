package university.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import university.IPrintable;

/**
 * Содержит основную информацию о аудитории: гомер кабинета, вместимость
 */

@Getter
@NoArgsConstructor
@ToString
public class Auditorium implements IPrintable {

    private int cabinetNumber;
    private int capacity;

    public Auditorium(int cabinetNumber, int capacity) {
        this.cabinetNumber = cabinetNumber;
        this.capacity = capacity;
    }

    @Override
    public void printInfo() {
        System.out.printf("""
                Номер кабинета: %d,
                Вместимость: %d%n,              
                """, cabinetNumber, capacity);
    }

}