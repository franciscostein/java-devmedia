package br.com.devmedia.appfinal.service;

import br.com.devmedia.appfinal.dao.DepartamentoDAO;
import br.com.devmedia.appfinal.entity.Departamento;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private static Logger logger = Logger.getLogger(DepartamentoService.class);

    @Autowired
    private DepartamentoDAO dao;

    public Departamento save(Departamento departamento) {

        return dao.save(departamento);
    }

    public void update(Departamento departamento) {

        dao.update(departamento);
    }

    public void saveOrUpdate(Departamento departamento) {

        if (departamento.getIdDepartamento() == null) {

            logger.info("Salvando departamento");

            dao.save(departamento);

            logger.info("Departamento " + departamento.getIdDepartamento() + "salvo com sucesso");

        } else {

            logger.info("Modificando departamento");

            dao.update(departamento);

            logger.info("Departamento " + departamento.getIdDepartamento() + "modificado com sucesso");
        }
    }

    public void delete(Integer id) {

        dao.delete(id);
    }

    public Departamento findById(Integer id) {

        return dao.findById(id);
    }

    public List<Departamento> findAll() {

        return dao.findAll();
    }
}
