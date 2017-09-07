package de.norbertspiess.spring.boot.mvc.validation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.norbertspiess.spring.boot.mvc.validation.model.GetBean;
import de.norbertspiess.spring.boot.mvc.validation.model.PostBean;
import de.norbertspiess.spring.boot.mvc.validation.validator.GetBeanValidator;
import de.norbertspiess.spring.boot.mvc.validation.validator.PostBeanValidator;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/validated")
@Slf4j
public class ValidatedController {
	private Validator getBeanValidator;

	private Validator postBeanValidator;

	@Autowired
	public ValidatedController(GetBeanValidator getBeanValidator, PostBeanValidator postBeanValidator) {
		this.getBeanValidator = getBeanValidator;
		this.postBeanValidator = postBeanValidator;
	}

	@InitBinder(value = "getBean")
	public void initGetValidator(WebDataBinder binder) {
		binder.setValidator(getBeanValidator);
	}

	@GetMapping
	public void validatedGetMethod(@ModelAttribute @Valid GetBean input) {
		log.info("received " + input);
	}

	@InitBinder(value = "postBean")
	public void initPostValidator(WebDataBinder binder) {
		binder.setValidator(postBeanValidator);
	}

	@PostMapping
	public void validatedPostMethod(@RequestBody @Valid PostBean input) {
		log.info("received " + input);
	}
}
