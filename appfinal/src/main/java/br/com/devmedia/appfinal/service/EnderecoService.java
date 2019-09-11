package br.com.devmedia.appfinal.service;

import br.com.devmedia.appfinal.dao.EnderecoDAO;
import br.com.devmedia.appfinal.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EnderecoService {

    @Autowired
    private EnderecoDAO dao;

    @Transactional(rollbackFor = Exception.class)       //Da um rollback caso de erro na inserção do funcionario, pra não ficar o endereço sem funcionario
    public Endereco saveOrUpdate(Endereco endereco) {

        if (endereco.getIdEndereco() == null) {

            dao.save(endereco);

        } else {

            dao.update(endereco);
        }

        return endereco;
    }

    public void delete(Integer id) {

        dao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Endereco findById(Integer id) {

        return dao.findById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Endereco> findAll() {

        return dao.findAll();
    }
}
