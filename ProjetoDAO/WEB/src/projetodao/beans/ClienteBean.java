package projetodao.beans;

import projetodao.dao.ClienteDAO;
import projetodao.dto.ClienteDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.List;

@ManagedBean(name = "cli")
@SessionScoped
public class ClienteBean {

    private ClienteDTO dto = new ClienteDTO();

    private ClienteDAO dao;

    private DataModel<ClienteDTO> clientes;

    public ClienteDTO getDto() {
        return dto;
    }

    public void setDto(ClienteDTO dto) {
        this.dto = dto;
    }

    public DataModel<ClienteDTO> getClientes() {

        dao = new ClienteDAO();

        List<ClienteDTO> lista = dao.getAll();

        clientes = new ListDataModel<ClienteDTO>(lista);

        return clientes;
    }

    public void setClientes(DataModel<ClienteDTO> clientes) {
        this.clientes = clientes;
    }

    public void selecionar() {
        dto = clientes.getRowData();
    }

    public void insert() {
        dao = new ClienteDAO();
    }

    public String save() {

        String retorno = "Erro ao cadastrar cliente";

        dao = new ClienteDAO();

        if (dao.insert(dto)) {
            retorno = "listar.xhtml";
        }

        return retorno;
    }

    public String update() {

        String retorno = "Erro ao atualizar cliente";

        dao = new ClienteDAO();

        if (dao.update(dto)) {
            retorno = "listar.xhtml";
        }

        return retorno;
    }

    public String delete() {

        String retorno = "Erro ao atualizar cliente";

        dao = new ClienteDAO();

        if (dao.delete(dto)) {
            retorno = "listar.xhtml";
        }

        return retorno;
    }
}
