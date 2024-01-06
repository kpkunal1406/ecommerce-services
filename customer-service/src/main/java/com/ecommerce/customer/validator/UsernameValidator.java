package com.ecommerce.customer.validator;

import com.ecommerce.customer.repository.CustomerRepository;
import com.ecommerce.customer.validator.constraint.UsernameConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String> {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z][A-Za-z0-9_]{7,29}$");
    private final CustomerRepository customerRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null) {
            return false;
        }

        if (!USERNAME_PATTERN.matcher(username).matches()) {
            return false;
        }

        if (customerRepository.findByUsername(username) != null) {
            return false;
        }

        return true;
    }
}
