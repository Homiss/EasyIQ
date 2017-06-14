package cn.wcode.filter;

import cn.wcode.SpringUtils;
import cn.wcode.constants.ErrorInfoConstants;
import cn.wcode.dto.Result;
import cn.wcode.model.User;
import cn.wcode.service.UserService;
import com.alibaba.fastjson.JSON;
import javax.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
/**
 * Created by jimin on 15/11/22.
 */
@Slf4j
@WebFilter(filterName="appLoginFilter")
public class AppLoginFilter implements Filter {

  @Autowired
  private UserService userService;

  @Override
  public void init(FilterConfig config) throws ServletException {
    log.info("login filter init config");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    userService = (UserService) SpringUtils.getBean("userService");

    HttpServletRequest req = (HttpServletRequest) request;
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    Map requestMap = request.getParameterMap();
    PrintWriter out = null;
    String url = req.getRequestURI();
    System.out.println("=================================================");
    System.out.println(JSON.toJSONString(requestMap));
    System.out.println(url);
    if(url.contains("/image/ios")){
      chain.doFilter(request, response);
      return;
    }
    if(req.getParameter("userId") == null || req.getParameter("token") == null){
      try {
        out = response.getWriter();
        out.write(JSON.toJSONString(new Result<>(false, HttpStatus.FORBIDDEN, ErrorInfoConstants.LOGIN_ERROR)));
        return;
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (out != null) {
          out.close();
        }
      }
    }
    Integer userId = Integer.valueOf(req.getParameter("userId"));
    String token = req.getParameter("token");
    System.out.println(userId + "  " + token);
    User user = userService.selectUserByToken(userId, token);
    if(user == null){ // 判断是否重复登陆
      try {
        out = response.getWriter();
        out.write(JSON.toJSONString(new Result<>(false, HttpStatus.FORBIDDEN, ErrorInfoConstants.LOGIN_ERROR)));
        return;
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (out != null) {
          out.close();
        }
      }
    }
    if(user.getEnabled() == 0){ // 用户不可用
      try {
        out = response.getWriter();
        out.write(JSON.toJSONString(new Result<>(false, HttpStatus.FORBIDDEN, ErrorInfoConstants.UNABLE_ERROR)));
        return;
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (out != null) {
          out.close();
        }
      }
    }
    chain.doFilter(request, response);
    return;
  }
  @Override
  public void destroy() {
    log.info("login filter destroy");
  }
}