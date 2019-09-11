package br.com.devmedia.blog.Service;

import br.com.devmedia.blog.entity.Autor;
import br.com.devmedia.blog.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Page<Autor> findByPagination(int page, int size) {

        Pageable pageable = new PageRequest(page, size);

        return repository.findAllByOrderByNomeAsc(pageable);
    }

    @Transactional(readOnly = false)
    public void save(Autor autor) {

        if (autor.getId() == null) {

            repository.save(autor); //insert

        } else { //update

            repository.updateNomeAndBiografia(autor.getNome(), autor.getBiografia(), autor.getId());
        }
    }

    public List<Autor> findAll() {

        return repository.findAll();
    }

    public Autor findByNome(String nome) {

        return repository.findByNome(nome);
    }

    public Autor findById(Long id) {

        return repository.findOne(id);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {

        repository.delete(id);
    }

    public Autor findByUsuario(Long id) {

        return repository.findByUsuarioId(id);
    }
}
