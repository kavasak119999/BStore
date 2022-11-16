package edu.max.bstore.exception.handling;

import edu.max.bstore.exception.RegistrationException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    public void handleMissingParams(MissingServletRequestParameterException ex){
//        System.out.println(ex.getMessage());
//    }

//    @ExceptionHandler({RegistrationException.class, LoginException.class})
//    public ModelAndView handleUserRegistrationException(RuntimeException ex) {
//        EmployeeIncorrectData data = new EmployeeIncorrectData();
//        data.setInfo(ex.getMessage());
//
//        return new ModelAndView("");
//    }

//    @ExceptionHandler({LoginException.class})
//    public ModelAndView handleUserLoginException(RuntimeException ex) {
//        EmployeeIncorrectData data = new EmployeeIncorrectData();
//        data.setInfo(ex.getMessage());
//
//        return new ModelAndView("");
//    }

}
