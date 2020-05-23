package pe.edu.upc.sysadap.spring.security.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.service.CompetenciaService;
import pe.edu.upc.sysadap.spring.security.web.dto.CompentenciaDto;

@Controller
@RequestMapping("/competenciaDTO")
public class CompetenciaDtoController {
    
    @Autowired
    private CompetenciaService competenciaService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping(value="guardar")
    public String registerProfesor(Model model,@ModelAttribute("competencia") @Valid CompentenciaDto competenciaDto,
                                      BindingResult result){

        if (result.hasErrors()){
            return "admin/mantComp :: competencia-form";
        }
        
        Competencia Competencia = competenciaService.findById(competenciaDto.getId());
        Competencia.setNombre(competenciaDto.getNombre());
        competenciaService.guardarCompetencia(Competencia);

        return "redirect:/mantComp?success";
    }

}
