package first.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		if(logger.isDebugEnabled()) {
			logger.debug("======================================= START =======================================");
			logger.debug("Request URL \t : " + request.getRequestURL());
		}
		return super.preHandle(request,response,handler);
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelandView) throws Exception{
		if(logger.isDebugEnabled()) {
			logger.debug("======================================= END =======================================\n");
		}
	}
}
