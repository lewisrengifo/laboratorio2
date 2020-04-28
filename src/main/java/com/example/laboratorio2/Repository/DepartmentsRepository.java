package com.example.laboratorio2.Repository;

import com.example.laboratorio2.Entity.departments;
import com.example.laboratorio2.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentsRepository extends JpaRepository<departments,String> {
    List<departments> findByDepartment_name(String name);
}
