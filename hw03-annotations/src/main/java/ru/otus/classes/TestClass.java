package ru.otus.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class TestClass {

    private Integer x;

    private Integer y;

    @Before
    private void added() {
        System.out.println("This annotation Before");
        x = 10;
        y = 0;
    }

    @Test
    private void calculationOne() {
        System.out.println("This annotation Test");
        int sum = x + y;
        System.out.println("Result: " + sum);
    }

    @Test
    private void calculationSecond() {
        System.out.println("This annotation Test 2");
        int sum = x / y;
        System.out.println("Result: " + sum);
    }

    @Test
    private void calculationThird() {
        System.out.println("This annotation Test 3");
        int sum = x * y;
        System.out.println("Result: " + sum);
    }

    @After
    private void remove() {
        System.out.println("This annotation After\n-----------------");
        x = null;
        y = null;
    }

}
