package com.example.testapp.repo;

import com.example.testapp.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DegreeRepo extends JpaRepository<Degree, Long> {
    Degree findById(long id);
}
