package cz.xentalib.interceptors;

import cz.xentalib.model.User;
import cz.xentalib.rest.UserContoler;
import cz.xentalib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UserServiceInterceptors implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        makeLog(request.toString());
    }


    public void makeLog(String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/files/logins.txt", true));
        writer.write(message);
        writer.close();
    }
}