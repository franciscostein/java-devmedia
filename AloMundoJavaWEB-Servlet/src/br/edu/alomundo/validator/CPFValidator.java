package br.edu.devmedia.crud.validator;

import br.edu.alomundo.comum.MensagemContantes;
import br.edu.alomundo.exception.ValidationException;
import br.edu.alomundo.util.Util;

import java.util.Map;

public class CPFValidator implements Validator {

    @Override
    public boolean validar(Map<String, Object> valores) throws ValidationException {

        String msgErro = "";

        for (String key : valores.keySet()) {

            String cpf = (String) valores.get(key);

            if (!"".equals(cpf)) {

                if (!Util.isCPF(cpf)) {

                    msgErro += MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", key).concat("<br/>");
                }

                if (cpf.length() < 11) {

                    msgErro += MensagemContantes.MSG_ERR_CPF_MENOR.replace("?", key).concat("<br/>");
                }

                if (cpf.length() > 11) {

                    msgErro += MensagemContantes.MSG_ERR_CPF_MAIOR.replace("?", key).concat("<br/>");
                }
            }
        }

        if (!"".equals(msgErro)) {

            throw new ValidationException(msgErro);
        }

        return true;
    }
}
