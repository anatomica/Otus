package ru.otus.classes;

import ru.otus.annotations.Log;

public interface TestLogging {

    @Log
    void calculation(int param1);

    @Log
    void calculation(int param1, int param2);

    @Log
    void calculation(int param1, int param2, String param3);

}
