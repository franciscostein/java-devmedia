package br.com.devmedia.blog.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria extends AbstractPersistable<Long> {

    @NotBlank       //Validação do que vem do formulario
    @Length(min = 3, max = 30)
    @Column(nullable = false, unique = true, length = 30)
    private String descricao;

    @Column(nullable = false, unique = true, length = 30)
    private String permalink;

    @ManyToMany
    @JoinTable(
            name = "postagens_has_categorias",
            joinColumns = @JoinColumn(name = "categoria_id"),
            inverseJoinColumns = @JoinColumn(name = "postagem_id")
    )
    private List<Postagem> postagens;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }
}
