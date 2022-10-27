package me.edrone.recruitmenttask.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IllegalNumberOfTargetStringsAdvice {

    @ResponseBody
    @ExceptionHandler(IllegalNumberOfTargetStringsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String IllegalNumberOfTargetStringsHandler(IllegalNumberOfTargetStringsException ex) {
        return ex.getMessage();
    }
}
