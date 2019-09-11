package br.com.devmedia.blog.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "avatares")
public class Avatar extends AbstractPersistable<Long> { //Com Abstract não precisa de id, equals n hash...

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String tipo;

    @Lob
    @Column(nullable = false)
    private byte[] avatar;

    public Avatar() {
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
