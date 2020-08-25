package br.com.verx.bp.config.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.verx.bp.service.exception.DefaultException;

@RestControllerAdvice
public class DataBaseExceptionHandler {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public List<ValidationExceptionDto> handlerDataIntegrityViolationException(DataIntegrityViolationException e) {
		String cause = e.getMostSpecificCause().getMessage();
		String causeTreated = "";

		if (cause.contains("ERROR: update or delete on table")) {
			StringBuilder sb = new StringBuilder("Erro ao atualizar ou remover item ");

			int itemIndex = cause.indexOf("table") + 7;
			int itemLength = cause.substring(itemIndex).indexOf("\"");
			String item = cause.substring(itemIndex, itemIndex + itemLength);

			sb.append(item);

			if (cause.contains("violates foreign key constraint")) {
				sb.append(", pois ele depende de um dos itens de ");

				int childItemIndex = cause.lastIndexOf("table") + 7;
				int childItemLength = cause.substring(childItemIndex).indexOf("\"");
				String childItem = cause.substring(childItemIndex, childItemIndex + childItemLength);

				sb.append(childItem);
			}

			sb.append(".");
			causeTreated = sb.toString();
		}

		if (cause.contains("ERROR: duplicate key")) {
			StringBuilder sb = new StringBuilder("Erro ao salvar ou atualizar campo ");

			int itemLastIndex = cause.lastIndexOf(")=") - 1;
			int itemIndex = cause.substring(0, itemLastIndex).lastIndexOf("(") + 1;
			String campo = cause.substring(itemIndex, itemLastIndex+1);

			sb.append(campo);

			sb.append(", pois o valor ");

			String valor = cause.substring(cause.lastIndexOf("(")+1, cause.lastIndexOf(")"));
			
			sb.append(valor + " já existe.");

			causeTreated = sb.toString();
		}

		List<ValidationExceptionDto> validationExceptionDtoList = new ArrayList<>();

		String defaultMessage = causeTreated.isEmpty() ? cause : causeTreated;
		ValidationExceptionDto validationExceptionDto = new ValidationExceptionDto("", defaultMessage);
		validationExceptionDtoList.add(validationExceptionDto);

		return validationExceptionDtoList;
	}
	
	// Another way to validate at the model level instead controller level
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public List<ValidationExceptionDto> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
		List<ValidationExceptionDto> validationExceptionDtoList = new ArrayList<>();
		Set<ConstraintViolation<?>> constraintErros = e.getConstraintViolations();

		constraintErros.forEach(c -> {
			String defaultMessage = c.getMessage();
			ValidationExceptionDto validationExceptionDto = new ValidationExceptionDto(c.getPropertyPath().toString(),
					defaultMessage);
			validationExceptionDtoList.add(validationExceptionDto);
		});

		return validationExceptionDtoList;
	}
	
	@ExceptionHandler(DefaultException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public List<ValidationExceptionDto> CustomerExceptionHandler(DefaultException e) {
		List<ValidationExceptionDto> validationExceptionDtoList = new ArrayList<>();

		String defaultMessage = e.getMessage();
		ValidationExceptionDto validationExceptionDto = new ValidationExceptionDto("", defaultMessage);
		validationExceptionDtoList.add(validationExceptionDto);

		return validationExceptionDtoList;
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public List<ValidationExceptionDto> RuntimeExceptionHandler(RuntimeException e) {
		List<ValidationExceptionDto> validationExceptionDtoList = new ArrayList<>();

		String defaultMessage = e.getMessage();
		ValidationExceptionDto validationExceptionDto = new ValidationExceptionDto("", defaultMessage);
		validationExceptionDtoList.add(validationExceptionDto);

		return validationExceptionDtoList;
	}

}
