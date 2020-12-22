package cn.github.yooryan;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author linyunrui
 */
@WebService
public class WebServiceServer {


    public static void main(String[] args) {
       String address = "http://localhost:9001/service/PrintName?wsdl";
        Endpoint.publish(address, new WebServiceServer());
        System.out.println("--发布成功--");
    }

    public String findUserInfoByName(String name){
        System.out.println("---------conn----------");
        return "server responses :" + name ;
    }

}
