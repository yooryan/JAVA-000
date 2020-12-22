package cn.github.yooryan;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * @author linyunrui
 */
public class WebServiceClient {

    public static void main(String[] args) {
        System.out.println(printName("123"));
    }


    public static Object printName(String name) {
        Object obj = null;
        try {
            JaxWsDynamicClientFactory factroy = JaxWsDynamicClientFactory.newInstance();
            //DynamicClientFactory factroy = DynamicClientFactory.newInstance();
            Client client = factroy.createClient("http://localhost:9001/service/PrintName?wsdl");
            Object[] results = client.invoke("findUserInfoByName", name);
            obj = results[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
