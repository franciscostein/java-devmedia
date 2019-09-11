package br.com.devmedia.blog.repository;

import br.com.devmedia.blog.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
