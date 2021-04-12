package br.com.locadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionCpfInvalido extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExceptionCpfInvalido(String excecao) {
		super(excecao);
	}

}
