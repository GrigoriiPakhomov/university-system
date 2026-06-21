package university.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import university.IPrintable;

/**
 * Содержит основную информацию об аудитории:
 * номер кабинета и вместимость.
 */
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Slf4j
public class Auditorium implements IPrintable {

    private int cabinetNumber;
    private int capacity;

    @Override
    public void printInfo() {
        log.info("""
                Номер кабинета: {},
                Вместимость: {}
                """, cabinetNumber, capacity);
    }
}