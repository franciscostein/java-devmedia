package projetodao.dto;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "padraodao.Clientes")
public class ClienteDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100)
    private String email;

    @Column(length = 9)
    private String cep;

    @Column(length = 14)
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<OsDTO> os;

    public ClienteDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        //String newCep = cep.substring(0, 5) + "-" + cep.substring(5, 8);
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        //String newCpf = cpf.substring(0, 3) + "." + cpf.substring(3, 3) + "." + cpf.substring(6, 3) + "-" + cpf.substring(9, 2);
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClienteDTO that = (ClienteDTO) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
