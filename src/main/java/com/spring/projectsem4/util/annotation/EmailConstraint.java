package com.spring.projectsem4.util.annotation;

import com.spring.projectsem4.util.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {

    String message() default "{invalid.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
