package br.com.devmedia.appfinal.service;

import br.com.devmedia.appfinal.dao.FuncionarioDAO;
import br.com.devmedia.appfinal.entity.Endereco;
import br.com.devmedia.appfinal.entity.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuncionarioService {

    @Autowired
    private FuncionarioDAO dao;

    @Autowired
    private EnderecoService enderecoService;

    public Funcionario saveOrUpdate(Funcionario funcionario) {

        Endereco endereco = enderecoService.saveOrUpdate(funcionario.getEndereco());

        funcionario.setEndereco(endereco);

        if (funcionario.getIdFuncionario() == null) {

            dao.save(funcionario);

        } else {

            dao.update(funcionario);
        }

        return funcionario;
    }

    public void delete(Integer id) {

        dao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Funcionario findById(Integer id) {

        return dao.findById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Funcionario> findAll() {

        return dao.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Funcionario> findByCargo(Integer idCargo) {

        return dao.findByCargo(idCargo);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Funcionario> findByNome(String nome) {

        return dao.findByNome(nome);
    }
}
