package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author linyunrui
 */
public class HelloMain {

    public static void main(String[] args) {
        try {
            Class<?> hello = new CustomClassloader().findClass("Hello");
            Method method = hello.getMethod("hello", null);
            method.invoke(hello.newInstance(),null);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

    }
}
