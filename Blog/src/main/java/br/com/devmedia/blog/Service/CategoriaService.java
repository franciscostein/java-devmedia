package br.com.devmedia.blog.Service;

import br.com.devmedia.blog.entity.Categoria;
import br.com.devmedia.blog.repository.CategoriaRepository;
import br.com.devmedia.blog.util.ReplaceString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Page<Categoria> findByPagination(int page, int size) {

        Pageable pageable = new PageRequest(page, size);

        return repository.findAllByOrderByDescricaoAsc(pageable);
    }

    public List<Categoria> findAll() {

        Sort sort = new Sort(new Order(Direction.ASC, "descricao"));

        return repository.findAll(sort);
    }

    public Categoria findByDescricao(String descricao) {

        return repository.findByDescricao(descricao);
    }

    public Categoria findById(Long id) {

        return repository.findOne(id);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {

        repository.delete(id);
    }

    @Transactional(readOnly = false)
    public void saveOrUpdate(Categoria categoria) {

        String permalink = ReplaceString.formatarPermalink(categoria.getDescricao());

        categoria.setPermalink(permalink);

        repository.save(categoria);
    }

    //Se quiser trabalhar com auditing pra auditar quando cria, tbm precisa extender AbstractAuditoria no lugar de Persistable
    /*@Transactional(readOnly = false)
	public void saveOrUpdate(Categoria categoria) {

		String permalink = ReplaceString.formatarPermalink(categoria.getDescricao());

		if (categoria.getId() != null) {
			Categoria cPersistente = repository.findOne(categoria.getId());
			cPersistente.setPermalink(permalink);
			cPersistente.setDescricao(categoria.getDescricao());
			repository.save(cPersistente);
		} else {
			categoria.setPermalink(permalink);
			repository.save(categoria);
		}
	}*/
}
