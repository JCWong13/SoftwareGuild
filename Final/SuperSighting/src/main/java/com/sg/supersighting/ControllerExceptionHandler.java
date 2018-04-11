/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersighting;

import com.sg.supersighting.LocationController.LocationAddException;
import com.sg.supersighting.LocationController.LocationDataException;
import com.sg.supersighting.OrganizationController.OrganizationAddException;
import com.sg.supersighting.SightingController.SightingAddException;
import com.sg.supersighting.SuperPersonController.SuperPersonAddException;
import com.sg.supersighting.SuperPowerController.SuperPowerAddException;
import com.sg.supersighting.SuperPowerController.SuperPowerDataException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author janie
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    //Response Body will send back JSON data in string format
    @ResponseBody
    //MethodArgumentNotValidException is special Spring exception that includes BindingResult.  Any other exception class
    // you declare will not have bindingresult
    public ErrorMessage processMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Your submisson has the following errors: <br/>");

        for (FieldError currentError : fieldErrors) {
            messageBuilder.append("[");
            messageBuilder.append(currentError.getField());
            messageBuilder.append(" : ");
            messageBuilder.append(currentError.getDefaultMessage());
            messageBuilder.append("]");
            messageBuilder.append("<br/>");
        }

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(messageBuilder.toString());
        return errorMessage;
    }

    @ExceptionHandler(UpdateIntegrityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processUpdateIntegrityException(UpdateIntegrityException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(LocationDataException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processLocationDataException(LocationDataException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(SuperPowerAddException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processSuperPowerAddException(SuperPowerAddException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(OrganizationAddException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processOrganizationAddException(OrganizationAddException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(LocationAddException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processLocationAddException(LocationAddException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(SightingAddException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processSightingAddException(SightingAddException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(SuperPersonAddException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processSuperPersonAddException(SuperPersonAddException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }
    

    public class UpdateIntegrityException extends Exception {
        public UpdateIntegrityException(String message) {
            super(message);
        }
    }

    public class ErrorMessage {

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
