package br.com.devmedia.curso.service;

import br.com.devmedia.curso.dao.CursoDao;
import br.com.devmedia.curso.domain.Curso;
import br.com.devmedia.curso.exception.IdNaoValidoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service        //Transactional mostra pro Spring que ele deve gerenciar todas as transações a partir dessa classe, deve ficar no service e não DAO
@Transactional
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoDao dao;

    @Override
    public void save(Curso curso) {
        dao.save(curso);
    }

    @Override
    public void update(Long id, Curso curso) {
        curso.setId(idValido(id));
        dao.findById(id);       //findById pois lançaremos uma exceção se o id for inválido, caso contrário não precisaria
        dao.update(curso);
    }

    @Override
    public void delete(Long id) {
        dao.delete(idValido(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Curso findById(Long id) {
        return dao.findById(idValido(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return dao.findAll();
    }

    @Override
    public Curso updateDataInicio(Long id, Date dataInicio) {
        Curso curso = dao.findById(id);
        curso.setDataInicio(dataInicio);    //Por ser um objeto persistente quando modificado já irá salvar as mudanças no BD
        return curso;
    }

    private Long idValido(Long id) {    //Dessa forma poupamos recursos não consultando ids fora da regra de negocio
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está inválido. Deve ser um valor inteiro maior que zero.");
        }
        return id;
    }
}
