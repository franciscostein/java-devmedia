package jdbc.bo;

import jdbc.dao.LoginDAO;
import jdbc.dto.LoginDTO;
import jdbc.exception.NegocioException;

public class LoginBO {

    public boolean logar(LoginDTO loginDTO) throws NegocioException {

        boolean resultado;

        try {

            if (loginDTO.getNome() == null || "".equals(loginDTO.getNome())) {

                throw new NegocioException("Usuário obrigatório!");

            } else if (loginDTO.getSenha() == null || "".equals(loginDTO.getSenha())) {

                throw new NegocioException("Senha obrigatório!");

            } else {

                LoginDAO loginDAO = new LoginDAO();

                resultado = loginDAO.logar(loginDTO);
            }

        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }

        return resultado;
    }
}
