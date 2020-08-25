package br.com.verx.bp.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {
	
	@Autowired
	MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationExceptionDto> handler(MethodArgumentNotValidException exception) {
		List<ValidationExceptionDto> validationExceptionDtoList = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(f -> {
			String defaultMessage = messageSource.getMessage(f, LocaleContextHolder.getLocale());
			ValidationExceptionDto validacaoExceptionDto = new ValidationExceptionDto(f.getField(), defaultMessage);
			validationExceptionDtoList.add(validacaoExceptionDto);
		});
		
		return validationExceptionDtoList;
	}

}
