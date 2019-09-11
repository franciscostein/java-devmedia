package br.com.devmedia.appfinal.service;

import br.com.devmedia.appfinal.dao.CargoDAO;
import br.com.devmedia.appfinal.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoDAO dao;

    public void saveOrUpdate(Cargo cargo) {

        if (cargo.getIdCargo() == null) {

            dao.save(cargo);

        } else {

            dao.update(cargo);
        }
    }

    public void delete(Integer id) {

        dao.delete(id);
    }

    public Cargo findById(Integer id) {

        return dao.findById(id);
    }

    public List<Cargo> findAll() {

        return dao.findAll();
    }

    public int getTotalPages(int size) {

        int count = dao.getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM cargos", Integer.class);

        return (int) Math.ceil(new Double(count) / new Double(size)); //ceil arredonda pra cima, se tiver 1.8 paginas, tem 1 inteira e 1 incompleta, ou seja, 2 paginas
    }

    public List<Cargo> findByPage(int page, int size) {

        return dao.findByPage((page - 1) * size, size);
    }
}
