package br.com.devmedia.blog.Service;

import br.com.devmedia.blog.entity.Comentario;
import br.com.devmedia.blog.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    @Transactional(readOnly = false)
    public void save(Comentario comentario) {

        comentario.setDataComentario(LocalDateTime.now());

        repository.save(comentario);
    }
}
