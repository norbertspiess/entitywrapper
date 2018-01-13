package de.norbertspiess.spring.boot.mvc.validation;

import de.norbertspiess.spring.boot.mvc.validation.model.GetBean;
import de.norbertspiess.spring.boot.mvc.validation.model.PostBean;
import de.norbertspiess.spring.boot.mvc.validation.validator.GetBeanValidator;
import de.norbertspiess.spring.boot.mvc.validation.validator.PostBeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;


@RestController
@RequestMapping("/validated")
@Slf4j
public class ValidatedController {
	private Validator getBeanValidator;

	private Validator postBeanValidator;

	@Inject
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
