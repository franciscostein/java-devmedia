package br.com.devmedia.unitedburger.service;

import br.com.devmedia.unitedburger.domain.dao.NotificacaoDAO;
import br.com.devmedia.unitedburger.domain.model.Notificacao;

public class NotificacaoService {

    private NotificacaoDAO notificacaoDAO = new NotificacaoDAO();

    public void cadastrar(Notificacao notificacao) {
        notificacaoDAO.cadastrar(notificacao);
    }
}
