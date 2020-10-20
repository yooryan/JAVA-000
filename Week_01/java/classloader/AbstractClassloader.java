package classloader;

import java.io.*;

/**
 * @author linyunrui
 */
public abstract class AbstractClassloader extends ClassLoader {

    protected String sourcesRoot;


    protected abstract Class<?> findCustomClass(String name);

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return findCustomClass(name);
    }
}
