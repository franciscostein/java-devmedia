package jdbc.bo;

import jdbc.dao.UfDAO;
import jdbc.dto.UfDTO;
import jdbc.exception.NegocioException;

import java.util.List;

public class UfBO {

    public List<UfDTO> listaUFs() throws NegocioException {

        List<UfDTO> lista = null;

        try {

            UfDAO ufDAO = new UfDAO();

            lista = ufDAO.listaEstados();

        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException(e.getMessage(), e);
        }

        return lista;
    }
}
