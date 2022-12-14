package com.kh.app.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.common.exception.MethodNotAllowedException;
import com.kh.app.student.model.dao.StudentDao;
import com.kh.app.student.model.dao.StudentDaoImpl;
import com.kh.app.student.model.service.StudentService;
import com.kh.app.student.model.service.StudentServiceImpl;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// url = controller 객체
	private Map<String, AbstractController> urlCommandMap = new HashMap<>();
	
	
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */
	public DispatcherServlet() throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//1. url-command.properties -> Properties 객체
		String filename = DispatcherServlet.class.getResource("/url-command.properties").getPath();
		Properties prop = new Properties();
		prop.load(new FileReader(filename));
		
		//2. Properties객체 -> urlCommandMap에 요소추가(String = AbstractController 객체)
		StudentDao studentDao = new StudentDaoImpl();
		StudentService studentService = new StudentServiceImpl(studentDao);
		
		Set<String> urls = prop.stringPropertyNames();
		for(String url : urls) {
			String className = prop.getProperty(url);
			Class<?> clz = Class.forName(className);
			Constructor<?> constructor = clz.getDeclaredConstructor(StudentService.class);
			AbstractController controller = (AbstractController) constructor.newInstance(studentService);
			urlCommandMap.put(url, controller);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. 요청주소
		String uri = request.getRequestURI();  // /hello-maven/student/studentEnroll.do
		uri = uri.replace(request.getContextPath(), "");  // /student/studentEnroll.do
		AbstractController controller = urlCommandMap.get(uri);
		
		if(controller == null) {
			throw new RuntimeException("해당 요청을 처리할 Controller가 존재하지 않습니다.");
		}
		
		//2. 해당 controller 호출
		String method = request.getMethod();
		String viewName = null;
		switch(method) {
		case "GET" : viewName = controller.doGet(request, response); break;
		case "POST" : viewName = controller.doPost(request, response); break;
		default : throw new MethodNotAllowedException(method);
		}
		//3. ViewName에 따라 forward/redirect 처리
		if(viewName != null) {
			if(viewName.startsWith("redirect:")) {
				//redirect
				String location = request.getContextPath() + viewName.replace("redirect:", "");
				response.sendRedirect(location);
			}
			else {
				//forward
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				viewName = prefix + viewName + suffix; // /WEB-INF/views/  student/studentList  .jsp
				request.getRequestDispatcher(viewName).forward(request, response);
			}
		}
		
		//viewName이 null인 경우 컨트롤러에서 응답메세지를 직접 작성한 경우 (비동기). 그때는 아무것도 하지 않는다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
