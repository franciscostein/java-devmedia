package br.com.devmedia.appfinal.web.validator;

import br.com.devmedia.appfinal.entity.Endereco;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EnderecoValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {

        return Endereco.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.logradouro", "error.logradouro");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.bairro", "error.bairro");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.cidade", "error.cidade");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco.estado", "error.estado");

        Endereco endereco = (Endereco) object;

        if (endereco.getNumero() != null) {

            if (endereco.getNumero() < 0) {

                errors.rejectValue("endereco.numero", "error.numero.negativo", "O campo Número não pode ser negativo!");
            }

        } else {

            errors.rejectValue("endereco.numero", "error.numero");
        }
    }
}
