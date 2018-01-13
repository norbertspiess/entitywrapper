package de.norbertspiess.spring.boot.mvc.validation.validator;

import de.norbertspiess.spring.boot.mvc.validation.model.PostBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.inject.Named;

import static java.util.Objects.isNull;


@Slf4j
@Named
public class PostBeanValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return PostBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PostBean input = (PostBean) target;
		if (isNull(input.getId())) {
			log.error("invalid input, id parameter is missing");
			throw new IllegalArgumentException();
		}
	}
}
