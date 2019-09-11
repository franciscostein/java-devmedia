package br.edu.devmedia.crud.validator;

import br.edu.devmedia.crud.exception.ValidationException;
import br.edu.devmedia.crud.util.MensagemContantes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class DataValidator implements Validator {

    public boolean validar(Map<String, Object> valores) throws ValidationException{

        String msgErro = "";

        for (String key : valores.keySet()) {

            String data = (String) valores.get(key);

            if (!"".equals(data)) {

                try {

                    if (data.length() < 10) {

                        msgErro += MensagemContantes.MSG_ERR_CAMPO_DATA_MENOR.replace("?", key).concat("<br/>");
                    }

                    if (data.length() > 11) {

                        msgErro += MensagemContantes.MSG_ERR_CAMPO_DATA_MAIOR.replace("?", key).concat("<br/>");
                    }

                    new SimpleDateFormat("dd/MM/yyyy").parse(data);

                } catch (ParseException e) {

                    msgErro += MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", key).concat("<br/>");
                }
            }
        }

        if (!"".equals(msgErro)) {

            throw new ValidationException(msgErro);
        }

        return true;
    }
}
