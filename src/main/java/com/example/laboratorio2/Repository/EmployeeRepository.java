package com.example.laboratorio2.Repository;

import com.example.laboratorio2.Entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<employee,String> {
}
