package br.com.devmedia.blog.repository;

import br.com.devmedia.blog.entity.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    Page<Postagem> findAllByOrderByDataPostagemDesc(Pageable pageable);

    Postagem findByPermalink(String permalink);

    List<Postagem> findByCategoriasPermalink(String link);

    List<Postagem> findByAutorNome(String nome);

    Page<Postagem> findAllByCategoriasPermalinkOrderByDataPostagemDesc(Pageable pageable, String permalink);

    Page<Postagem> findAllByAutorIdOrderByDataPostagemDesc(Pageable pageable, Long id);

    //Aqui é obrigado a usar o Pageable, PageRequest da excessão
    Page<Postagem> findByTextoContainingIgnoreCaseOrderByDataPostagemDesc(String texto, Pageable pageable);

    Page<Postagem> findAllByTituloContainingIgnoreCaseOrderByDataPostagemDesc(Pageable pageable, String titulo);

    Page<Postagem> findAllByAutorIdAndTituloContainingIgnoreCaseOrderByDataPostagemDesc(Pageable pageable, Long id, String titulo);
}
