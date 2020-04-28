package com.example.laboratorio2.Repository;

import com.example.laboratorio2.Entity.location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<location, String> {
}
