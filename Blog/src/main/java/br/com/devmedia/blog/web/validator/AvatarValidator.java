package br.com.devmedia.blog.web.validator;

import br.com.devmedia.blog.entity.Avatar;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AvatarValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        return Avatar.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Avatar avatar = (Avatar) target;

        if (avatar.getFile() != null) {

            if (avatar.getFile().getSize() == 0) {

                errors.rejectValue("file", "file", "Selecione um avatar até 100kb");
            }
        }
    }
}
