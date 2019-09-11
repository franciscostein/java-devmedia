package br.com.devmedia.blog.Service;

import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional(readOnly = false)
    public void save(Usuario usuario) {

        if (usuario.getDataCadastro() == null) {

            usuario.setDataCadastro(LocalDate.now());
        }

        String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());

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
