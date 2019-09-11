package br.edu.alomundo.dto;

import java.io.Serializable;
import java.util.Date;

public class PessoaDTO implements Serializable {

    private String nome;

    private String endereco;

    private Long cpf;

    private Date dtNasc;

    private Date dtExedicao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public Date getDtExedicao() {
        return dtExedicao;
    }

    public void setDtExedicao(Date dtExedicao) {
        this.dtExedicao = dtExedicao;
    }
}
