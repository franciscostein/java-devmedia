package br.com.devmedia.java.cadastro.model;

import br.com.devmedia.java.cadastro.model.dao.CadastroDao;
import br.com.devmedia.java.cadastro.model.entities.Cadastro;
import br.com.devmedia.java.cadastro.model.entities.Estado;
import br.com.devmedia.java.cadastro.model.entities.EstadoCivil;
import br.com.devmedia.java.cadastro.model.entities.Sexo;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CadastroBean {

    private Cadastro cadastro = new Cadastro();
    private CadastroDao cadastroDao = new CadastroDao();

    public Cadastro getCadastro() {
        return cadastro;
    }

    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }

    public Estado[] getEstado() {
        return Estado.values();
    }

    public EstadoCivil[] getEstadoCivil() {
        return EstadoCivil.values();
    }

    public Sexo[] getSexo() {
        return Sexo.values();
    }

    public String cadastrar() {
        cadastroDao.insert(cadastro);
        cadastro = new Cadastro();

        return "sucesso";
    }
}
