package br.com.devmedia.blog.repository;

import br.com.devmedia.blog.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByDescricao(String descricao);

    Page<Categoria> findAllByOrderByDescricaoAsc(Pageable pageable);
}
