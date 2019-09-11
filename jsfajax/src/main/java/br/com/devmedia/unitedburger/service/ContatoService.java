package br.com.devmedia.unitedburger.service;

import br.com.devmedia.unitedburger.domain.dao.ContatoDAO;
import br.com.devmedia.unitedburger.domain.model.Contato;

public class ContatoService {

    private ContatoDAO contatoDAO = new ContatoDAO();

    public void cadastrar(Contato contato) {
        contatoDAO.cadastrar(contato);
    }
}
