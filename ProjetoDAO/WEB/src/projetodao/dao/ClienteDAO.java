package projetodao.dao;

import projetodao.dto.ClienteDTO;
import projetodao.util.Hibernate;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDAO {

    private static final String tbl_name = "clientes";
    private static final String sql_insert = "";
    private static final String sql_update = "";
    private static final String sql_delete = "";
    private static final String sql_select = "";
    private static final String sql_by_id = "";

    public boolean insert(ClienteDTO clienteDTO) {
        return false;
    }

    public boolean update(ClienteDTO clienteDTO) {
        return false;
    }

    public boolean delete(ClienteDTO clienteDTO) {
        return false;
    }

    public List<ClienteDTO> getAll() {

        EntityManager em = Hibernate.criarEntityManager();

        List<ClienteDTO> clientes = em.createQuery("select cliente from ClienteDTO cliente").getResultList();

        return clientes;
    }

    public List<ClienteDTO> getById(int id) {
        return null;
    }
}
