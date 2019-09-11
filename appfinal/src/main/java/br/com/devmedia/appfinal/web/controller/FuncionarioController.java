package br.com.devmedia.appfinal.web.controller;

import br.com.devmedia.appfinal.entity.Cargo;
import br.com.devmedia.appfinal.entity.Funcionario;
import br.com.devmedia.appfinal.service.CargoService;
import br.com.devmedia.appfinal.service.EnderecoService;
import br.com.devmedia.appfinal.service.FuncionarioService;
import br.com.devmedia.appfinal.web.editor.CargoEditorSupport;
import br.com.devmedia.appfinal.web.editor.StringToDoubleEditorSupport;
import br.com.devmedia.appfinal.web.editor.StringToIntegerEditorSupport;
import br.com.devmedia.appfinal.web.validator.EnderecoValidator;
import br.com.devmedia.appfinal.web.validator.FuncionarioValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController {

    private static Logger logger = Logger.getLogger(FuncionarioController.class);

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private EnderecoService enderecoService;

    @InitBinder     //Transforma o id do Cargo em um Objeto Cargo
    protected void initBinder(ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Cargo.class, new CargoEditorSupport(cargoService));

        binder.registerCustomEditor(Integer.class, "endereco.numero", new StringToIntegerEditorSupport());

        binder.registerCustomEditor(Double.class, "salario", new StringToDoubleEditorSupport());

        binder.addValidators(new FuncionarioValidator(new EnderecoValidator()));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView findAll(@ModelAttribute("funcionario") Funcionario funcionario) {

        ModelAndView modelAndView = new ModelAndView("addFuncionario");

        modelAndView.addObject("funcionarios", funcionarioService.findAll());
        modelAndView.addObject("cargos", cargoService.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(@ModelAttribute("funcionario") @Validated Funcionario funcionario, BindingResult bindingResult,
                       ModelMap modelMap) {

        if (bindingResult.hasErrors()) {

            logger.warn("Foram encontrados campos inválidos!");

            modelMap.addAttribute("funcionarios", funcionarioService.findAll());
            modelMap.addAttribute("cargos", cargoService.findAll());

            return new ModelAndView("addFuncionario", modelMap);
        }

        try {

            logger.info("Executando saveOrUpdate para funcionário!");

            funcionarioService.saveOrUpdate(funcionario);

            logger.info("Operação realizada com sucesso para funcionário id: " + funcionario.getIdFuncionario());

        } catch (Exception e) {

            logger.error("Erro ao inserir/alterar funcionário", e);
        }

        return new ModelAndView("redirect:/funcionario/add");
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Integer id, ModelMap model) {

        model.addAttribute("funcionario", funcionarioService.findById(id));
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("cargos", cargoService.findAll());

        return new ModelAndView("addFuncionario");
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        Funcionario funcionario = funcionarioService.findById(id);

        //Por causa do cascade, a chave estrangeira ta em funcionario, não endereço
        enderecoService.delete(funcionario.getEndereco().getIdEndereco());

        return "redirect:/funcionario/add";
    }

    @RequestMapping(value = "/find/cargo/{idCargo}", method = RequestMethod.GET)
    public ModelAndView findByCargo(@PathVariable("idCargo") Integer idCargo,
                                    @ModelAttribute("funcionario") Funcionario funcionario, ModelMap model) {

        model.addAttribute("funcionarios", funcionarioService.findByCargo(idCargo));
        model.addAttribute("cargos", cargoService.findAll());

        return new ModelAndView("addFuncionario", model);
    }

    @RequestMapping(value = "/find/nome/{nome}")
    public ModelAndView findByNome(@PathVariable("nome") String nome,
                                   @ModelAttribute("funcionario") Funcionario funcionario, ModelMap model) {

        model.addAttribute("funcionarios", funcionarioService.findByNome(nome));
        model.addAttribute("cargos", cargoService.findAll());

        return new ModelAndView("addFuncionario", model);
    }
}
