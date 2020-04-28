package com.example.laboratorio2.Controller;


import com.example.laboratorio2.Entity.departments;
import com.example.laboratorio2.Entity.employee;
import com.example.laboratorio2.Entity.jobs;
import com.example.laboratorio2.Repository.DepartmentsRepository;
import com.example.laboratorio2.Repository.EmployeeRepository;
import com.example.laboratorio2.Repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller

public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JobsRepository jobsRepository;
    @Autowired
    DepartmentsRepository departmentsRepository;


    @GetMapping("/listaemp")
    public String lista(Model model){
        List<employee> listaEmpleados = employeeRepository.findAll();
        model.addAttribute("listaEmpleados", listaEmpleados);
        List<departments> listaDepartamentos = departmentsRepository.findAll();
        model.addAttribute("listaDepartamentos", listaDepartamentos);
        List<jobs> listaTrabajos = jobsRepository.findAll();
        model.addAttribute("listaTrabajos", listaTrabajos);

        return "/employees/listaemp";
    }
    @PostMapping(value="/buscarEmpleado")
    public String buscarempleado(@RequestParam("searchfield") String searchField, Model model) {

        List<employee> listaEmpleados = employeeRepository.findByFirst_name(searchField);
        model.addAttribute("emplyeeList", listaEmpleados);
        return"/listaemp";
    }

    @GetMapping("crear")
    public String newEmployee(Model model){
        return"/employee/nuevoemp";
    }
    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") int id, RedirectAttributes redirectAttributes){
        Optional<employee> opt = employeeRepository.findById(id);
        if(opt.isPresent()) {
            employeeRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje2", "Eliminado Exitosamente");
        }
        return "redirect:/listaemp";







}
