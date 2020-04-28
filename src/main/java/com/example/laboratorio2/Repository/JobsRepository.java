package com.example.laboratorio2.Repository;

import com.example.laboratorio2.Entity.jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<jobs,String> {

}
