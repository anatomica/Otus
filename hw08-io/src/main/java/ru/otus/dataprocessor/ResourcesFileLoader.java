package ru.otus.dataprocessor;

import ru.otus.model.Measurement;
import ru.otus.utils.JsonUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class ResourcesFileLoader implements Loader {

    private final JsonUtils jsonUtils;

    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        jsonUtils = new JsonUtils();
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат
        String line, json = "";
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        try (var br = new BufferedReader(new FileReader(Objects.requireNonNull(url).getPath()))) {
            System.out.println("text from the json: ");
            while (isNotBlank(line = br.readLine())) {
                json = new String(line.getBytes(), UTF_8);
                System.out.println(json);
            }
        } catch (IOException e) {
            throw new FileProcessException(String.format("Error loading %s: %s", fileName, e.getMessage()));
        }

        if (isNotBlank(json)) {
            return jsonUtils.decodeList(json, Measurement.class);
        }

        return emptyList();
    }

}
