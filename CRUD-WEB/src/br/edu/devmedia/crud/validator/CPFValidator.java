package br.edu.devmedia.crud.validator;

import br.edu.devmedia.crud.exception.ValidationException;
import br.edu.devmedia.crud.util.MensagemContantes;
import br.edu.devmedia.crud.util.Util;

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

            } else {

                msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", key).concat("<br/>");
            }
        }

        if (!"".equals(msgErro)) {

            throw new ValidationException(msgErro);
        }

        return true;
    }
}
