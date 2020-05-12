package com.HospitalV2.HospitalV2.Repository;

import com.HospitalV2.HospitalV2.models.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HRepository extends JpaRepository<Patients, Integer> {

    List<Patients> findByFirstNameContainingOrLastNameContaining(String searchTerm, String searchTerm1);
    @Query("FROM Patients p WHERE p.age > ?1")
    List<Patients> findByAgeGreaterThan(Integer age);

    Patients findByFirstName(String firstName);
}
