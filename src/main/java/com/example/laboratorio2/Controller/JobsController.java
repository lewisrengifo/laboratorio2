package com.example.laboratorio2.Controller;

import com.example.laboratorio2.Entity.departments;
import com.example.laboratorio2.Entity.jobs;
import com.example.laboratorio2.Repository.DepartmentsRepository;
import com.example.laboratorio2.Repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jobs")
public class JobsController {

    @Autowired
    JobsRepository jobsRepository;
    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping("")
    public String primera(){
        return "index";
    }

    @GetMapping("/lista")
    public String listajobs(Model model){
        List<jobs> listaJobs = jobsRepository.findAll();
        model.addAttribute("lista",listaJobs);
        return "/jobs/listajobs";
    }
    @PostMapping("/buscar")
    public String buscarXsalary(Model model, @RequestParam("salary") int salary){
        List<jobs> listaJobs = jobsRepository.findAll();
        List<jobs>lista= new ArrayList<>();
        for (jobs j:listaJobs){
            if(j.getMin_salary()<salary && j.getMax_salary()<salary){
                lista.add(j);
            }
        }
        model.addAttribute("lista",lista);
        return "/jobs/listaBuscada";
    }
    @GetMapping ("/agregarJobs")
    public String agregarJobs(Model model){
        List<departments> listadep = departmentsRepository.findAll();
        model.addAttribute("lista",listadep);
        return "/jobs/agregarJobs";
    }

    @PostMapping("/guardar")
    public String guardar(jobs j, @RequestParam("valor") String valor, @RequestParam("nom") String nom,RedirectAttributes redirectAttributes){
        List<jobs> listaJobs = jobsRepository.findAll();
        departments d =departmentsRepository.findByDepartment_name(valor);
        String id=d.getDepartment_short_name() "+" nom;
        int a=0;
        for(jobs j:listaJobs){
            if(j.getJob_id().equals(id)) a=1;
        }
        if(a==1){
            jobsRepository.save(j);
            return "/jobs/lista";
        }
        else{
            redirectAttributes.addFlashAttribute("mensaje","Vuelva a ingresar");
            return "jobs/agregar";
        }
    }


    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") String id,RedirectAttributes redirectAttributes){
        Optional<jobs> opt = jobsRepository.findById(id);
        if(opt.isPresent()) {
            jobsRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje2", "Eliminado Exitosamente");
        }
        return "redirect:/lista";
    }

}
