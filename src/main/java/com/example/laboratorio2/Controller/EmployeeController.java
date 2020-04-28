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

import java.util.List;

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







}
