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
	 * 由@ModelAttribute标记的方法，会在每个牧歌方法执行之前被Spring MVC调用
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map) {
		if(id != null) {
			//模拟从数据库中获取对象
			User user = new User("1", "123456", "tom@163.com", 15);
			System.out.println("从数据库中取一个对象：" + user);
			
			map.put("user", user);
		}
	}
	
	/**
	 * 必须要注释掉@SessionAttributes注解，否则会抛异常
	 * 程序运行流程：
	 * 1.执行ModelAttribute注解修饰的方法，从数据库中取出对象，把对象放入到了Map中，键为user
	 * 2.SpringMVC从Map中 取出user对象，并把表单的请求参数赋给该User对象的对应属性
	 * 3.SpringMVC把上述对象传入目标方法的参数
	 * 
	 * 注意：在ModelAttribute修饰的方法中，放入到Map时的键需要和目标方法入参类型的第一个字母小写的字符串一致!!!
	 * 
	 * 源代码分析的流程
	 * 1.调用@ModelAttribute注解修饰的方法，实际上把@ModelAttribute方法中Map的数据放到implicitModel中
	 * 2.解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的target属性
	 * 1)创建WebDataBinder对象：
	 * ①.确定objectName属性，若传入的attrName属性值为"",则objectName为类名第一个字母小写
	 * 	注意:attrName.若目标方法的POJO属性使用了@ModelAttribute来修饰,则attrName值即为@ModelAttribute的value属性值
	 * 
	 * ②.确定target属性
	 * 	>在implicitModel中查找attrName对应的属性值，若存在,ok
	 * 	>若不存在:则验证当前Handler是否使用了@SessionAttributes进行修饰,若使用了,
	 * 	   则尝试从Session中获取attrName所对应的属性值,若Session中没有对应的属性值,则抛出异常
	 *  >若Handler没有使用@SessionAttributes进行修饰,或@SessionAttributes中没有使用value值指定的key和attrName相匹配
	 *   则通过反射创建了POJO对象
	 * 2)SpringMVC把表单请求参数赋给了WebDataBinder的target对应的属性
	 * 3)SpringMVC会把WebDataBinder的attrName和target给到implicitModel
	 * 4)把WebDataBinder的target作为参数传递给目标方法的入参
	 * @param user
	 * @return
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user) {
		System.out.println("change:" + user);
		return SUCCESS;
	}
	
	/**
	 * @SessionAttributes 除了可以通过属性名制定需要放到会话中的属性外，(value)
	 * 还可以通过模型属性的对象类型指定那些模型属性需要放到会话中(types)
	 * 注意：该注解只能放到类的上面
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
	 * 目标方法可以添加Map类型（实际上也可以是Model类型或ModelMap类型）的参数
	 * @param map
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map) {
		map.put("names", Arrays.asList("mike", "jerry", "rose"));
		return SUCCESS;
	}
	
	/**
	 * 目标方法的返回值可以是ModelAndView
	 * 可以包含视图值和模型信息
	 * Spring MVC会把ModelAndView的model中数据放入到request域对象中
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView() {
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		//添加模型数据到ModelAndView中
		modelAndView.addObject("time", new Date());
		return modelAndView;
	}
	
	/**
	 * 可以使用Servlet 原生的API作为目标方法的参数
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
	 * Spring MVC会按请求参数名和POJO属性名进行自动匹配
	 * 自动为该对象填充属性值，支持级联属性
	 * 如：address.city,address.city.number等
	 * @param user
	 * @return
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println("testPojo:" + user);
		return SUCCESS;
	}
	
	/**
	 * @CookieValue:映射一个cookie值，属性同@RequestParam，只做了解即可
	 * @param sessionID
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionID) {
		System.out.println("testCookieValue, sessionID:" + sessionID);
		return SUCCESS;
	}
	
	/**
	 * 用法同@RequestParam
	 * 作用：映射请求头，做一了解
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
	 * value属性为请求参数
	 * required：该参数是否必须，默认值为：true
	 * defaultValue：请求参数的默认值
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
	 * Rest风格的URL
	 * 以CRUD为例
	 * 新增：/order POST
	 * 修改：/order/1 PUT update?id=1
	 * 获取：/order/1 GET get?id=1
	 * 删除：/order/1 DELETE delete?id=1
	 * 
	 * 如何发送put请求和delete请求呢?
	 * 1.需要配置HiddenHttpMethodFilter
	 * 2.需要发送post请求
	 * 3.需要在发送post请求时携带一个name="_method" 的隐藏域，值为PUT或DELETE
	 * 
	 * 在SpringMVC的目标方法中如何得到id呢?
	 * 使用@PathVariable注解
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
	 * @PathVariable可以映射URL的占位符到目标方法的参数中
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable" + id);
		return SUCCESS;
	}
	
	/**
	 * 其中*表示任意字符
	 * @return
	 */
	@RequestMapping("/testAntPath/*/test")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * 可以使用params和headers来更加精确的映射请求，params和headers支持简单的表达式
	 * @return
	 */
	@RequestMapping(value="testParamsAndHeaders", params={"username", "age!=10"}, headers={"Accept-Language=en-US,zh;q=0.8"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	/**
	 * 使用method属性指定请求方式
	 * @return
	 */
	@RequestMapping(value="/testMethod", method=RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 * 1.RequestMapping除了修饰方法，还可以来修饰类
	 * 2.类定义处，提供初步的请求映射信息，相对于web应用的根目录
	 * 3.方法定义处：提供进一步的细分映射信息，相对于类定义出的URL，若类定义处未标注@RequestMapping，则方法处标记的相对应于web应用的根目录
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
