package br.com.devmedia.blog.Service;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Perfil;
import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Page<Usuario> findByPaginationOrderByField(int page, int size, String field, String order) {

        Sort sort = new Sort(new Order(Direction.fromString(order), field));

        return repository.findAll(new PageRequest(page, size, sort));
    }

    public Page<Usuario> findByPagination(int page, int size) {

        Pageable pageable = new PageRequest(page, size);

        return repository.findAllByOrderByNomeAsc(pageable);
    }

    @Transactional(readOnly = false)
    public void updatePerfil(Usuario usuario) {

        Usuario persistente = repository.findOne(usuario.getId());
        persistente.setPerfil(usuario.getPerfil());

        repository.save(persistente);
    }

    @Transactional(readOnly = false)
    public void updateSenha(Usuario usuario) {

        String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());

        Usuario uPersistente = repository.findOne(usuario.getId());

        uPersistente.setSenha(hash);

        //usuario.setSenha(hash);       MODIFICADO POR CAUSA DO AUDITING
        //repository.updateSenha(usuario.getSenha(), usuario.getId());

        repository.save(uPersistente);
    }

    @Transactional(readOnly = false)
    public void updateNomeAndEmail(Usuario usuario) {

        Usuario uPersistente = repository.findOne(usuario.getId());
        uPersistente.setNome(usuario.getNome());
        uPersistente.setEmail(usuario.getEmail());

        // Usava apenas a linha abaixo antes do Auditing, mas o auditing só funciona com o save do Spring Jpa, no metodo abaixo estava salvando com @Modifing
        //repository.updateNomeAndEmail(usuario.getNome(), usuario.getEmail(), usuario.getId());

        repository.save(uPersistente);
    }

    @Transactional(readOnly = false)
    public void save(Usuario usuario) {

        if (usuario.getDataCadastro() == null) {

            usuario.setDataCadastro(LocalDate.now());
        }

        String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());

        usuario.setSenha(hash);
        usuario.setPerfil(Perfil.LEITOR);

        repository.save(usuario);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {

        repository.delete(id);
    }

    public Usuario findById(Long id) {

        return repository.findOne(id);
    }

    public Usuario findByAvatar(Avatar avatar) {

        return  repository.findByAvatar(avatar);
    }

    public Usuario findByEmail(String email) {

        return repository.findByEmail(email);
    }

    public List<Usuario> findAll() {

        return repository.findAll();
    }
}