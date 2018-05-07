package faust.validate;

import faust.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordValidator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    public void validate(Object object, Errors errors) {
        User user = (User) object;
        if (user.isEditingPassword()) {
            //check if attempted to change existing password
            String currentHash = user.getCurrentPasswordHash();  // <-- gets value from user_change_password.jsp hidden input
            if (currentHash != null) {
                String passwordAttempt = user.getCurrentPassword();
                if (!passwordEncoder.matches(passwordAttempt, currentHash)) {
                    // user guess not matching with existing
                    errors.rejectValue("currentPassword", "valid.passwordInvalidCurrent");
                    return;
                }
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "valid.passwordNoCurrent");
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "valid.password");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "valid.passwordConfirm");
            if (!errors.hasErrors() && !user.getPassword().equals(user.getPasswordConfirm())) {
                errors.rejectValue("passwordConfirm", "valid.passwordNotMatch");
            }
        }
    }
}
