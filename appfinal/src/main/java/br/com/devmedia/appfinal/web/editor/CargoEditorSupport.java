package br.com.devmedia.appfinal.web.editor;

import br.com.devmedia.appfinal.entity.Cargo;
import br.com.devmedia.appfinal.service.CargoService;

import java.beans.PropertyEditorSupport;

public class CargoEditorSupport extends PropertyEditorSupport {

    private CargoService service;

    public CargoEditorSupport(CargoService service) {
        this.service = service;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (!text.isEmpty()) {

            Integer id = Integer.parseInt(text);

            Cargo cargo = service.findById(id);

            super.setValue(cargo);
        }
    }
}
