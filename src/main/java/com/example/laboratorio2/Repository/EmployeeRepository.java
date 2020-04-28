package com.example.laboratorio2.Repository;

import com.example.laboratorio2.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<employee,String> {



    List<employee> findByFirst_name(String nombre);

}
