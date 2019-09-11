package bean;

import dao.LembreteDAO;
import model.Lembrete;
import model.Prioridade;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@SessionScoped
public class LembreteBean {

    private Lembrete lembrete;
    private LembreteDAO lembreteDAO;
    private List<Lembrete> lembretes;

    @PostConstruct
    public void init() {
        lembreteDAO = new LembreteDAO();
        lembrete = new Lembrete();

        try {
            lembretes = lembreteDAO.listarTodos();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public Lembrete getLembrete() {
        return lembrete;
    }

    public List<Lembrete> getLembretes() {
        return lembretes;
    }

    public String inserir() {
        try {
            lembreteDAO.inserir(lembrete);

            lembretes = lembreteDAO.listarTodos();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Lembrete adicionado com sucesso!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }

        return "home";
    }

    public String atualizar() {
        try {
            lembreteDAO.atualizar(lembrete);

            lembretes = lembreteDAO.listarTodos();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Lembrete editado com sucesso!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }

        return "home";
    }

    public String excluir() {
        try {
            lembreteDAO.excluir(lembrete.getId());

            lembretes = lembreteDAO.listarTodos();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Lembrete removido com sucesso!"));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
        }

        return "home";
    }

    public void selecionar() {
        try {
            lembrete = lembreteDAO.selecionar(lembrete.getId());

            if (lembrete == null || lembrete.getId() == 0) {
                lembrete = new Lembrete();

                throw new Exception("Lembrete não encontrado.");
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public Prioridade[] getPrioridades() {
        return Prioridade.values();
    }
}