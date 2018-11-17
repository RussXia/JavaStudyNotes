package com.xzy.hibernate.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author RuzzZZ
 * @since 26/01/2018 2:31 PM
 */
@Slf4j
public class ValidatorHandler {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static void main(String[] args) {
        Validator validator = validatorFactory.getValidator();
        Entity entity = new Entity(10, "Test");
        entity.getNameAndAge();
        Set<ConstraintViolation<Entity>> set = validator.validate(entity);
        set.forEach(entityConstraintViolation -> {
            log.error(entityConstraintViolation.getInvalidValue() + " : "
                    + entityConstraintViolation.getMessage());
        });
    }
}