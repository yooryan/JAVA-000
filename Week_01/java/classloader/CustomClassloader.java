package classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author linyunrui
 */
public class CustomClassloader extends AbstractClassloader {

    private static final String DEFALUG_SOURCEROOT = "/Users/yoolin/IdeaProjects/JAVA-000/Week_01/resources/";


    public CustomClassloader() {
       this(DEFALUG_SOURCEROOT);
    }

    public CustomClassloader(String sourceRoot) {
        super.sourcesRoot = sourceRoot;
    }

    @Override
    protected Class<?> findCustomClass(String name) {
        String filePath = sourcesRoot + name + ".xlass";
        File file = new File(filePath);
        byte[] bytes = null;
        int index = 0;
        try (
                FileInputStream fileInputStream = new FileInputStream(file)){
            int read;
            bytes = new byte[1024];
            while ((read =fileInputStream.read()) != -1){
                bytes[index++] = (byte) (255 - read);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return defineClass(name,bytes,0,index);

    }
}
