package br.com.devmedia.unitedburger.domain.bean;

import br.com.devmedia.unitedburger.domain.model.Contato;
import br.com.devmedia.unitedburger.domain.model.Notificacao;
import br.com.devmedia.unitedburger.service.ContatoService;
import br.com.devmedia.unitedburger.service.NotificacaoService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped
public class ContatoBean {

    private Contato contato = new Contato();
    private ContatoService contatoService = new ContatoService();

    private Notificacao notificacao = new Notificacao();
    private NotificacaoService notificacaoService = new NotificacaoService();

    private boolean receberNotificacao;

    public Contato getContato() {
        return contato;
    }

    public boolean getReceberNotificacao() {
        return receberNotificacao;
    }

    public void setReceberNotificacao(boolean receberNotificacao) {
        this.receberNotificacao = receberNotificacao;
    }

    public void cadastrar(ActionEvent event) {
        FacesMessage message;

        try {
            contatoService.cadastrar(contato);

            if (receberNotificacao) {
                notificacao.setEmail(contato.getEmail());
                notificacaoService.cadastrar(notificacao);
            }

            contato = null;     //Limpa o e-mail do campo na tela

            message = new FacesMessage("E-mail cadastrado com sucesso!");
        } catch (RuntimeException e) {
            message = new FacesMessage("Serviço temporariamente indisponível");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}