package br.com.devmedia.appfinal.web.validator;

import br.com.devmedia.appfinal.entity.Funcionario;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class FuncionarioValidator implements Validator {

    private EnderecoValidator enderecoValidator;

    public FuncionarioValidator(EnderecoValidator enderecoValidator) {

        this.enderecoValidator = enderecoValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return Funcionario.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "error.nome", "O campo nome é obrigatório!");

        Funcionario funcionario = (Funcionario) object;

        if (funcionario.getSalario() != null) {

            if (funcionario.getSalario() < 0) {

                errors.rejectValue("salario", "error.salario", "O salário não pode ser negativo");
            }

        } else {

            errors.rejectValue("salario", "error.salario", "O campo Salário é obrigatório!");
        }

        if (funcionario.getDataEntrada() != null) {

            LocalDate atual = LocalDate.now();

            if (funcionario.getDataEntrada().isAfter(atual)) {

                errors.rejectValue("dataEntrada", "error.dataEntrada", "O data de entrada tem que ser anterior ou igual a hoje " + atual);
            }

        } else {

            errors.rejectValue("dataEntrada", "error.dataEntrada", "O campo Data Entrada é obrigatório!");
        }

        if (funcionario.getDataSaida() != null) {

            if (funcionario.getDataSaida().isBefore(funcionario.getDataEntrada())) {

                errors.rejectValue("dataSaida", "error.dataSaida",
                        "O data de entrada tem que ser posterior ou igual a data de entrada " + funcionario.getDataEntrada());
            }
        }

        if (funcionario.getCargo() == null) {

            errors.rejectValue("cargo", "error.cargo", "O campo campo Cargo é obrigatório!");
        }

        ValidationUtils.invokeValidator(enderecoValidator, funcionario.getEndereco(), errors);
    }
}
