package ru.otus;

import ru.otus.classes.TestClass;
import ru.otus.classes.TestLoader;

public class Launcher {

    public static void main(String[] args) {
        TestLoader.loadTest(TestClass.class);
    }

}
