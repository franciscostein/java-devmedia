package br.edu.devmedia.crud.bo;

import br.edu.devmedia.crud.dao.UsuarioDAO;
import br.edu.devmedia.crud.dto.UsuarioDTO;
import br.edu.devmedia.crud.exception.NegocioException;
import br.edu.devmedia.crud.util.MensagemContantes;
import br.edu.devmedia.crud.validator.LoginValidator;

import java.util.HashMap;
import java.util.Map;

public class UsuarioBO {

    public boolean validarUsuario(UsuarioDTO usuarioDTO) throws NegocioException {

        boolean isValid = true;

        try {
            //Valida campos obrigatórios
            Map<String, Object> valores = new HashMap<>();
            valores.put("Usuário", usuarioDTO.getUsuario());
            valores.put("Senha", usuarioDTO.getSenha());

            if (new LoginValidator().validar(valores)) {

                isValid = true;
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            isValid = usuarioDAO.validarUsuario(usuarioDTO);

            if (!isValid) {

                throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new NegocioException(e);
        }

        return isValid;
    }
}
