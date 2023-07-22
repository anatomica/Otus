package ru.otus.classes;

import lombok.SneakyThrows;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class TestLoader {

    private TestLoader() {
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static <T> void loadTest(Class<T> clazz) {
        Class<TestClass> testClass = (Class<TestClass>) clazz;
        Statistics statistics = new Statistics(0, 0, 0);
        List<Method> tests = new ArrayList<>();
        Method methodBefore = null;
        Method methodAfter = null;

        for (Method method : testClass.getDeclaredMethods()) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Before) {
                    methodBefore = method;
                } else if (annotation instanceof Test) {
                    tests.add(method);
                } else if (annotation instanceof After) {
                    methodAfter = method;
                }
            }
        }

        tests.sort(comparing(Method::getName));
        Statistics stats = invokeTests(methodBefore, tests, methodAfter, testClass, statistics);
        printStatistics(stats);
    }

    private static Statistics invokeTests(Method methodBefore, List<Method> tests, Method methodAfter,
                                          Class<TestClass> clazz, Statistics statistics) {
        tests.forEach(test -> {
            Constructor<TestClass> constructor;
            TestClass newInstance;
            try {
                constructor = clazz.getConstructor();
                newInstance = constructor.newInstance();
            } catch (NoSuchMethodException | InvocationTargetException |
                     InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            try {
                if (methodBefore != null) {
                    methodBefore.setAccessible(true);
                    methodBefore.invoke(newInstance);
                }
            } catch (IllegalAccessException | InvocationTargetException ignored) {
            }
            try {
                test.setAccessible(true);
                test.invoke(newInstance);
                statistics.setSuccess(statistics.getSuccess() + 1);
            } catch (IllegalAccessException | InvocationTargetException | ArithmeticException e) {
                statistics.setFailed(statistics.getFailed() + 1);
                System.out.println("Exception -> " + e.getCause().getMessage());
            } finally {
                statistics.setTotal(statistics.getTotal() + 1);
            }
            try {
                if (methodAfter != null) {
                    methodAfter.setAccessible(true);
                    methodAfter.invoke(newInstance);
                }
            } catch (IllegalAccessException | InvocationTargetException ignored) {
            }
        });

        return statistics;
    }

    private static void printStatistics(Statistics stats) {
        System.out.printf("Total tests: %s\nSuccess tests: %s\nFailure tests: %s\n",
                stats.getTotal(), stats.getSuccess(), stats.getFailed());
    }

}
