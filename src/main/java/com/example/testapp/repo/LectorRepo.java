package com.example.testapp.repo;

import com.example.testapp.entity.Lector;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectorRepo extends JpaRepository<Lector, Long> {
    Lector findById(long id);

    @Transactional
    @Query(value = "SELECT avg(d.salary) FROM degree d JOIN lector l on l.degree_id = d.id join lector_departments ld on l.id = ld.lector_id join department dep on dep.id = ld.departments_id where dep.name = :name", nativeQuery = true)
    double findAverageSalaryByDepartmentName(@Param("name") String name);

    @Transactional
    @Query(value = "SELECT count(l.id) FROM lector l join lector_departments ld on l.id = ld.lector_id join department dep on dep.id = ld.departments_id where dep.name = :name", nativeQuery = true)
    int findLectorsCountByDepartmentName(@Param("name") String name);

    @Transactional
    @Query(value = "SELECT l.name FROM lector l WHERE l.name LIKE %:template%", nativeQuery = true)
    List<String> findByNameContaining(@Param("template") String template);

}
