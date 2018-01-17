package me.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC中的请求处理器
 * @author Administrator
 */
@Controller
public class HelloWorld {

	/**
	 * 1.使用RequestMapping注解来映射请求的URL
	 * 2.返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver会作如下的视图解析：
	 * 通过prefix + returnVal + 后缀得到实际的物理视图，然后做转发操作
	 *  /WEB-INF/views/success.jsp
	 * @return
	 */
	@RequestMapping("/HelloWorld")
	public String helloWorld() {
		System.out.println("Hello, World!");
		return "success";
	}
}
