package br.com.devmedia.appfinal.web.controller;

import br.com.devmedia.appfinal.entity.Cargo;
import br.com.devmedia.appfinal.entity.Departamento;
import br.com.devmedia.appfinal.service.CargoService;
import br.com.devmedia.appfinal.service.DepartamentoService;
import br.com.devmedia.appfinal.web.editor.DepartamentoEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cargo")
public class CargoController {

    private static final int PAGE_DEFAULT = 1;

    private static final int PAGE_SIZE_DAFAULT = 5;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;

    @InitBinder //primeiro metodo executado pelo controller
    protected void initBinder(ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Departamento.class, new DepartamentoEditorSupport(departamentoService));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView findAll(@ModelAttribute("cargo") Cargo cargo, ModelMap model) {

        model.addAttribute("cargos", cargoService.findByPage(PAGE_DEFAULT, PAGE_SIZE_DAFAULT));
        model.addAttribute("departamentos", departamentoService.findAll());
        model.addAttribute("current", PAGE_DEFAULT);
        model.addAttribute("total", cargoService.getTotalPages(PAGE_SIZE_DAFAULT));

        return new ModelAndView("addCargo", model);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("cargo") Cargo cargo) {

        cargoService.saveOrUpdate(cargo);

        return "redirect:/cargo/add";
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id) {

        Cargo cargo = cargoService.findById(id);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("addCargo");
        modelAndView.addObject("cargo", cargo);
        modelAndView.addObject("cargos", cargoService.findByPage(PAGE_DEFAULT, PAGE_SIZE_DAFAULT));
        modelAndView.addObject("current", PAGE_DEFAULT);
        modelAndView.addObject("total", cargoService.getTotalPages(PAGE_SIZE_DAFAULT));
        modelAndView.addObject("departamentos", departamentoService.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        cargoService.delete(id);

        return "redirect:/cargo/add";
    }

    @RequestMapping(value = "/page/{page}")
    public ModelAndView pagination(@PathVariable("page") Integer page, @ModelAttribute("cargo") Cargo cargo) {

        ModelAndView model = new ModelAndView("addCargo");

        model.addObject("departamentos", departamentoService.findAll());
        model.addObject("cargos", cargoService.findByPage(page, PAGE_SIZE_DAFAULT));
        model.addObject("current", page);
        model.addObject("total", cargoService.getTotalPages(PAGE_SIZE_DAFAULT));

        return model;
    }
}
