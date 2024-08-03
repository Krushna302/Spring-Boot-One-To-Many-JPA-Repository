package Com.Example.Demo.Exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
	
	
	
	@ExceptionHandler(ExceptionResponse.class)
	public @ResponseBody ExceptionData handleException (HttpServletRequest request ,ExceptionResponse exception) {
		
		
		ExceptionData data = new ExceptionData();
		
		data.setUri(request.getRequestURI());
		
		data.setErrormsg(exception.getMessage());
		
		
		return data;
		
		
	}
	
	

}
