package ru.otus.proxies;

import org.apache.commons.collections4.CollectionUtils;
import ru.otus.annotations.Log;
import ru.otus.classes.TestLogging;
import ru.otus.classes.TestLoggingImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@SuppressWarnings("all")
public class Ioc {

    private Ioc() {
    }

    public static TestLogging createProxyClass() {
        InvocationHandler handler = new MyInvocationHandler(new TestLoggingImpl());
        return (TestLogging)
                Proxy.newProxyInstance(
                        Ioc.class.getClassLoader(),
                        new Class<?>[]{TestLogging.class},
                        handler);
    }

    static class MyInvocationHandler implements InvocationHandler {
        private final TestLogging myClass;

        MyInvocationHandler(TestLogging myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            checkAnnotations(method, args);
            return method.invoke(myClass, args);
        }

        private void checkAnnotations(Method method, Object[] args) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Log) {
                    createLog(method, args);
                }
            }
        }

        private void createLog(Method method, Object[] args) {
            if (isNotEmpty(args)) {
                String values = getValues(Arrays.asList(args));
                System.out.println("executed method: " + method.getName() + ", param: " + values);
            }
        }

        private static String getValues(List<?> values) {
            if (CollectionUtils.isNotEmpty(values)) {
                return values.stream().map(String::valueOf).collect(Collectors.joining(","));
            }
            return EMPTY;
        }

        @Override
        public String toString() {
            return "MyInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }

}
