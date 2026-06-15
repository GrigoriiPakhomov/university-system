package university.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuditoriumTest {

    @Test
    @DisplayName("Проверка создания аудитории")
    void testCreateAuditorium() {
        Auditorium auditorium = new Auditorium(208, 30);

        assertThat(auditorium.getCabinetNumber()).isEqualTo(208);
        assertThat(auditorium.getCapacity()).isEqualTo(30);
    }
}