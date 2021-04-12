package br.com.locadora.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.locadora.exception.ExceptionCliente;
import br.com.locadora.exception.ExceptionCpfInvalido;
import br.com.locadora.exception.ExceptionFilme;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	 @ExceptionHandler(ExceptionCpfInvalido.class)
	  public final ResponseEntity<ExceptionResponse> handlerCpfInvalido(ExceptionCpfInvalido ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }
	 
	 @ExceptionHandler(ExceptionCliente.class)
	  public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(ExceptionCliente ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	 
	 @ExceptionHandler(ExceptionFilme.class)
	  public final ResponseEntity<ExceptionResponse> handleFilmeNotFoundExcption(ExceptionFilme ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	 

}
