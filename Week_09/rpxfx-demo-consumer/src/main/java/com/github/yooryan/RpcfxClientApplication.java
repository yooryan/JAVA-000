package com.github.yooryan;


import com.github.yooryan.api.Order;
import com.github.yooryan.api.OrderService;
import com.github.yooryan.client.Rpcfx;
import com.github.yooryan.client.RpcfxBytebuddy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class RpcfxClientApplication {

	// 二方库
	// 三方库 lib
	// nexus, userserivce -> userdao -> user
	//

	public static void main(String[] args) {

		// UserService service = new xxx();
		// service.findById

		//OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
		//final OrderService orderService = Rpcfx.create(OrderService.class,"http://localhost:8080/");

	//	final OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
		final OrderService orderService = RpcfxBytebuddy.create(OrderService.class, "http://localhost:8080/");
		Order order = orderService.findOrderById(1992129);
		System.out.println(String.format("find order name=%s, amount=%f",order.getName(),order.getAmount()));

		// 新加一个OrderService


		//SpringApplication.run(RpcfxClientApplication.class, args);

		/*final OrderService bean = run.getBean(OrderService.class);
		final Order order = bean.findOrderById(2);
		System.out.println(String.format("find order name=%s, amount=%f",order.getName(),order.getAmount()));*/
	}


}
