import ru.otus.classes.TestLogging;
import ru.otus.proxies.Ioc;

public class ProxyLogging {

    public static void main(String[] args) {
        TestLogging testLogging = Ioc.createProxyClass();
        testLogging.calculation(3);
        testLogging.calculation(3, 4);
        testLogging.calculation(3, 4, "5");
    }

}