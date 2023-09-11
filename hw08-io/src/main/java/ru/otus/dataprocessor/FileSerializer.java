package ru.otus.dataprocessor;

import ru.otus.utils.JsonUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.apache.commons.collections4.MapUtils.isNotEmpty;

public class FileSerializer implements Serializer {

    private final JsonUtils jsonUtils;

    private final String fileName;

    public FileSerializer(String fileName) {
        jsonUtils = new JsonUtils();
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        // формирует результирующий json и сохраняет его в файл
        if (isNotEmpty(data)) {
            String json = jsonUtils.encode(data);
            try (var bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
                bufferedWriter.write(json);
            } catch (IOException e) {
                throw new FileProcessException(String.format("Error saving %s: %s", fileName, e.getMessage()));
            }
        }
    }

}
