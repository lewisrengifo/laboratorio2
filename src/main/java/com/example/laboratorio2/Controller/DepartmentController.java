package com.example.laboratorio2.Controller;

import com.example.laboratorio2.Entity.location;
import com.example.laboratorio2.Entity.departments;
import com.example.laboratorio2.Entity.employee;
import com.example.laboratorio2.Repository.DepartmentsRepository;
import com.example.laboratorio2.Repository.EmployeeRepository;
import com.example.laboratorio2.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

public class DepartmentController {
    @Autowired
    DepartmentsRepository deparepo;
    @Autowired
    LocationRepository locarepo;
    @Autowired
    EmployeeRepository emprepo;

    @GetMapping("/listadepa")
    public String listadepa(Model model) {
        List<departments> listadepa = deparepo.findAll();
        List<location> listaloca = locarepo.findAll();
        model.addAttribute("listaloca", listaloca);
        model.addAttribute("listadep", listadepa);
        return "departments/listadep";
    }
    @GetMapping("/creardepa")
    public String creardepa(Model model) {
        List<location> listaloca = locarepo.findAll();
        model.addAttribute("listaloca", listaloca);
        List<employee> listaemp = emprepo.findAll();
        model.addAttribute("listaemp", listaemp);
        List<departments> listadepa = deparepo.findAll();
        model.addAttribute("listadepa", listadepa);
        return "departments/crear";
    }
    @PostMapping("/guardardepa")
    public String guardardeoa(departments departments, RedirectAttributes redirectAttributes) {
        if (departments.getDepartment_id() == 0) {
            List<departments> listaActual = deparepo.findAll();
            departments.setDepartment_id((listaActual.size() * 10) + 10);
            redirectAttributes.addFlashAttribute("mensaje", "Departamento Creado Exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Departamento Actualizado Exitosamente");
        }
        deparepo.save(departments);
        return "redirect:/listadepa";
    }
    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") int id,RedirectAttributes redirectAttributes){
        Optional<departments> opt = deparepo.findById(id);
        if(opt.isPresent()) {
            deparepo.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje2", "Eliminado Exitosamente");
        }
        return "redirect:/listaDepa";
    }
}
