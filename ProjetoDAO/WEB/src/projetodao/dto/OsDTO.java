package projetodao.dto;

import javax.persistence.*;

@Entity
@Table(name = "padraodao.OS")
public class OsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String titulo;

    @Column
    private String conteudo;

    @ManyToOne
    @JoinColumn
    private ClienteDTO cliente;

    public OsDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OsDTO osDTO = (OsDTO) o;

        return id.equals(osDTO.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
