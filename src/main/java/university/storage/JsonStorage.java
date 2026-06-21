package university.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import university.repository.UniversityRepository;

import java.io.File;
import java.io.IOException;

/**
 * Класс для сохранения и загрузки данных
 * в JSON-файл.
 * <p>
 * Использует библиотеку Jackson.
 */
@Slf4j
public class JsonStorage {
    private static final String FILE = "university-data.json";
    private final ObjectMapper objectMapper;
    public JsonStorage() {
        this.objectMapper = new ObjectMapper();
    }

    public void save(UniversityRepository repository) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE), repository);
            log.info("Данные успешно сохранены");
        } catch (IOException e) {
            log.error("Ошибка сохранения файла {}", FILE, e);
            throw new RuntimeException("Не удалось сохранить данные", e);
        }
    }

    public UniversityRepository load() {
        File file = new File(FILE);
        if (!file.exists()) {
            log.info("Файл данных не найден, создан пустой репозиторий");
            return new UniversityRepository();
        }
        try {
            UniversityRepository repository = objectMapper.readValue(file, UniversityRepository.class);
            log.info("Данные успешно загружены");
            return repository;
        } catch (IOException e) {
            log.error("Ошибка загрузки файла {}", FILE, e);
            return new UniversityRepository();
        }
    }
}