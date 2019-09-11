package br.com.devmedia.notas.managedBean;

import br.com.devmedia.notas.model.Nota;
import br.com.devmedia.notas.rest.NotaREST;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class NotaBean {

    private Integer id;
    private Nota nota;
    private List<Nota> notas;
    private NotaREST notaREST = new NotaREST();

    public void initDetalhes(){
        if (this.id != null) {
            this.nota = notaREST.obter(id);
        } else {
            this.nota = new Nota();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Nota getNota() {
        return nota;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    @PostConstruct
    public void init() {
        notas = notaREST.listar();
    }

    public String exibir(Nota nota) {
        this.nota = nota;
        return "detalhes";
    }

    public String remover(Integer id) {
        notaREST.remover(id);
        this.notas = notaREST.listar();
        return "index";
    }

    public String editar() {
        if (this.id != null) {
            notaREST.atualizar(this.nota);
        } else {
            notaREST.inserir(this.nota);
        }
        this.notas = notaREST.listar();
        return "index";
    }
}
