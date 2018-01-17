package me.springmvc.handlers;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import me.springmvc.entities.User;

@SessionAttributes(value={"user"}, types={String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

	private static final String SUCCESS = "success";
	
	/**
	 * ��@ModelAttribute��ǵķ���������ÿ�����跽��ִ��֮ǰ��Spring MVC����
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if(id != null) {
			//ģ������ݿ��л�ȡ����
			User user = new User("1", "123456", "tom@163.com", 15);
			System.out.println("�����ݿ���ȡһ������" + user);
			
			map.put("user", user);
		}
	}
	
	/**
	 * ����Ҫע�͵�@SessionAttributesע�⣬��������쳣
	 * �����������̣�
	 * 1.ִ��ModelAttributeע�����εķ����������ݿ���ȡ�����󣬰Ѷ�����뵽��Map�У���Ϊuser
	 * 2.SpringMVC��Map�� ȡ��user���󣬲��ѱ����������������User����Ķ�Ӧ����
	 * 3.SpringMVC������������Ŀ�귽���Ĳ���
	 * 
	 * ע�⣺��ModelAttribute���εķ����У����뵽Mapʱ�ļ���Ҫ��Ŀ�귽��������͵ĵ�һ����ĸСд���ַ���һ��!!!
	 * 
	 * Դ�������������
	 * 1.����@ModelAttributeע�����εķ�����ʵ���ϰ�@ModelAttribute������Map�����ݷŵ�implicitModel��
	 * 2.��������������Ŀ�������ʵ���ϸ�Ŀ�����������WebDataBinder�����target����
	 * 1)����WebDataBinder����
	 * ��.ȷ��objectName���ԣ��������attrName����ֵΪ"",��objectNameΪ������һ����ĸСд
	 * 	ע��:attrName.��Ŀ�귽����POJO����ʹ����@ModelAttribute������,��attrNameֵ��Ϊ@ModelAttribute��value����ֵ
	 * 
	 * ��.ȷ��target����
	 * 	>��implicitModel�в���attrName��Ӧ������ֵ��������,ok
	 * 	>��������:����֤��ǰHandler�Ƿ�ʹ����@SessionAttributes��������,��ʹ����,
	 * 	   ���Դ�Session�л�ȡattrName����Ӧ������ֵ,��Session��û�ж�Ӧ������ֵ,���׳��쳣
	 *  >��Handlerû��ʹ��@SessionAttributes��������,��@SessionAttributes��û��ʹ��valueֵָ����key��attrName��ƥ��
	 *   ��ͨ�����䴴����POJO����
	 * 2)SpringMVC�ѱ��������������WebDataBinder��target��Ӧ������
	 * 3)SpringMVC���WebDataBinder��attrName��target����implicitModel
	 * 4)��WebDataBinder��target��Ϊ�������ݸ�Ŀ�귽�������
	 * @param user
	 * @return
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user) {
		System.out.println("change:" + user);
		return SUCCESS;
	}
	
	/**
	 * @SessionAttributes ���˿���ͨ���������ƶ���Ҫ�ŵ��Ự�е������⣬(value)
	 * ������ͨ��ģ�����ԵĶ�������ָ����Щģ��������Ҫ�ŵ��Ự��(types)
	 * ע�⣺��ע��ֻ�ܷŵ��������
	 * @param map
	 * @return
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		User user = new User("tom", "143143", "hello", 14);
		map.put("user", user);
		map.put("school", "JavaSchool");
		return SUCCESS;
	}
	
	/**
	 * Ŀ�귽���������Map���ͣ�ʵ����Ҳ������Model���ͻ�ModelMap���ͣ��Ĳ���
	 * @param map
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		map.put("names", Arrays.asList("mike", "jerry", "rose"));
		return SUCCESS;
	}
	
	/**
	 * Ŀ�귽���ķ���ֵ������ModelAndView
	 * ���԰�����ͼֵ��ģ����Ϣ
	 * Spring MVC���ModelAndView��model�����ݷ��뵽request�������
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		//���ģ�����ݵ�ModelAndView��
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}
	
	/**
	 * ����ʹ��Servlet ԭ����API��ΪĿ�귽���Ĳ���
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/testServletAPI")
	public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("testServletAPI," + request + "," + response);
		return SUCCESS; 
	}
	
	/**
	 * Spring MVC�ᰴ�����������POJO�����������Զ�ƥ��
	 * �Զ�Ϊ�ö����������ֵ��֧�ּ�������
	 * �磺address.city,address.city.number��
	 * @param user
	 * @return
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println("testPojo:" + user);
		return SUCCESS;
	}
	
	/**
	 * @CookieValue:ӳ��һ��cookieֵ������ͬ@RequestParam��ֻ���˽⼴��
	 * @param sessionID
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionID) {
		System.out.println("testCookieValue, sessionID:" + sessionID);
		return SUCCESS;
	}
	
	/**
	 * �÷�ͬ@RequestParam
	 * ���ã�ӳ������ͷ����һ�˽�
	 * @param al
	 * @return
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String al) {
		System.out.println("testRequestHeader, Accept-Language:" + al);
		return SUCCESS;
	}
	
	/**
	 * @RequestParam
	 * value����Ϊ�������
	 * required���ò����Ƿ���룬Ĭ��ֵΪ��true
	 * defaultValue�����������Ĭ��ֵ
	 * @param username
	 * @param age
	 * @return
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String username, 
			@RequestParam(value="age", required=true, defaultValue="0") int age) {
		System.out.println("testRequestParam, username:" + username + ",age:" + age);
		return SUCCESS;
	}
	
	/**
	 * Rest����URL
	 * ��CRUDΪ��
	 * ������/order POST
	 * �޸ģ�/order/1 PUT update?id=1
	 * ��ȡ��/order/1 GET get?id=1
	 * ɾ����/order/1 DELETE delete?id=1
	 * 
	 * ��η���put�����delete������?
	 * 1.��Ҫ����HiddenHttpMethodFilter
	 * 2.��Ҫ����post����
	 * 3.��Ҫ�ڷ���post����ʱЯ��һ��name="_method" ��������ֵΪPUT��DELETE
	 * 
	 * ��SpringMVC��Ŀ�귽������εõ�id��?
	 * ʹ��@PathVariableע��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.GET)
	public String testRest(@PathVariable Integer id) {
		System.out.println("testRest" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest", method=RequestMethod.POST)
	public String testRestPost() {
		System.out.println("testRest Post");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest Delete" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest Put" + id);
		return SUCCESS;
	}
	
	/**
	 * @PathVariable����ӳ��URL��ռλ����Ŀ�귽���Ĳ�����
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable" + id);
		return SUCCESS;
	}
	
	/**
	 * ����*��ʾ�����ַ�
	 * @return
	 */
	@RequestMapping("/testAntPath/*/test")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * ����ʹ��params��headers�����Ӿ�ȷ��ӳ������params��headers֧�ּ򵥵ı��ʽ
	 * @return
	 */
	@RequestMapping(value="testParamsAndHeaders", params={"username", "age!=10"}, headers={"Accept-Language=en-US,zh;q=0.8"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	/**
	 * ʹ��method����ָ������ʽ
	 * @return
	 */
	@RequestMapping(value="/testMethod", method=RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 * 1.RequestMapping�������η�������������������
	 * 2.�ඨ�崦���ṩ����������ӳ����Ϣ�������webӦ�õĸ�Ŀ¼
	 * 3.�������崦���ṩ��һ����ϸ��ӳ����Ϣ��������ඨ�����URL�����ඨ�崦δ��ע@RequestMapping���򷽷�����ǵ����Ӧ��webӦ�õĸ�Ŀ¼
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
