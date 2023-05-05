package com.example.testapp.repo;

import com.example.testapp.entity.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Department findById(long id);
    Department findByName(String name);

    @Transactional
    @Query(value = "SELECT count(l.id) as count From testapp.lector l inner join testapp.lector_departments ld on l.id = ld.lector_id inner join testapp.department d on d.id = ld.departments_id where l.degree_id = 1 and d.name = :name ", nativeQuery = true)
    int countAssistantsByDepartmentName(@Param("name") String name);

    @Transactional
    @Query(value = "SELECT count(l.id) as count From testapp.lector l inner join testapp.lector_departments ld on l.id = ld.lector_id inner join testapp.department d on d.id = ld.departments_id where l.degree_id = 2 and d.name = :name", nativeQuery = true)
    int countAssociateProfessorsByDepartmentName(@Param("name") String name);

    @Transactional
    @Query(value = "SELECT count(l.id) as count From testapp.lector l inner join testapp.lector_departments ld on l.id = ld.lector_id inner join testapp.department d on d.id = ld.departments_id where l.degree_id = 3 and d.name = :name", nativeQuery = true)
    int countProfessorsByDepartmentName(@Param("name") String name);

}
