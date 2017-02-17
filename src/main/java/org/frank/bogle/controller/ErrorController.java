package org.frank.bogle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by frankbogle on 10/02/2017.
 */
@ControllerAdvice
public class ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView controllerException(HttpServletRequest request, Exception ex) throws Exception {
        logger.error("ControllerException thrown by class ErrorController");

        if (AnnotationUtils.findAnnotation
                (ex.getClass(), ResponseStatus.class) != null)
        throw ex;

        return new ModelAndView("error")
                .addObject("exception", ex)
                .addObject("url", request.getRequestURL());
    }

}
