package org.ada.cloud.zuul.filter;

import javax.servlet.http.HttpServletResponse;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**  
 * Filename: ErrorFilter.java  <br>
 *
 * Description: 异常统一处理  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年5月25日 <br>
 *
 *  
 */

public class ErrorFilter extends ZuulFilter {
	 	Logger log = LoggerFactory.getLogger(ErrorFilter.class);
	    @Override
	    public String filterType() {
	        return "error";
	    }
	    @Override
	    public int filterOrder() {
	        return 10;
	    }
	    @Override
	    public boolean shouldFilter() {
	        return true;
	    }
	    @Override
	    public Object run() {
	        RequestContext ctx = RequestContext.getCurrentContext();
	        Throwable throwable = ctx.getThrowable();
	        log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());
	        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        ctx.set("error.exception", throwable.getCause());
	        return null;
	    }
}