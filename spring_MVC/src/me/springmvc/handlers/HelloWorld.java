package me.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC�е���������
 * @author Administrator
 */
@Controller
public class HelloWorld {

	/**
	 * 1.ʹ��RequestMappingע����ӳ�������URL
	 * 2.����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ������InternalResourceViewResolver�������µ���ͼ������
	 * ͨ��prefix + returnVal + ��׺�õ�ʵ�ʵ�������ͼ��Ȼ����ת������
	 *  /WEB-INF/views/success.jsp
	 * @return
	 */
	@RequestMapping("/HelloWorld")
	public String helloWorld() {
		System.out.println("Hello, World!");
		return "success";
	}
}
