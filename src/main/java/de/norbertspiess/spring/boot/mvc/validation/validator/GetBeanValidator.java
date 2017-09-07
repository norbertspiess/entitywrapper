package de.norbertspiess.spring.boot.mvc.validation.validator;

import static java.util.Objects.isNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.norbertspiess.spring.boot.mvc.validation.model.GetBean;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class GetBeanValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return GetBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GetBean input = (GetBean) target;
		if (isNull(input.getId())) {
			log.error("invalid input, id parameter is missing");
			throw new IllegalArgumentException();
		}
	}
}
