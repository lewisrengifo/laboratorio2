package com.example.laboratorio2.Repository;

import com.example.laboratorio2.Entity.departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<departments,String> {
}
