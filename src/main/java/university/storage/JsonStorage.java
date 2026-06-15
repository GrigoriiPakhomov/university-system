package university.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import university.repository.UniversityRepository;

import java.io.File;
import java.io.IOException;

/**
 * Класс для сохранения и загрузки данных
 * в JSON-файл.
 * <p>
 * Использует библиотеку Jackson.
 */

public class JsonStorage {
    private static final String FILE = "university-data.json";
    private final ObjectMapper objectMapper;

    public JsonStorage() {
        this.objectMapper = new ObjectMapper();
    }

    public void save(UniversityRepository repository) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE), repository);
            System.out.println("Данные сохранены");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения файла");
        }
    }

    public UniversityRepository load() {
        File file = new File(FILE);
        if (!file.exists()) {
            return new UniversityRepository();
        }
        try {
            return objectMapper.readValue(file, UniversityRepository.class
            );
        } catch (IOException e) {
            System.out.println("Ошибка загрузки файла");
            return new UniversityRepository();
        }
    }
}