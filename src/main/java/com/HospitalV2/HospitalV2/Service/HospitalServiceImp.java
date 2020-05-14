package com.HospitalV2.HospitalV2.Service;

import com.HospitalV2.HospitalV2.Repository.HRepository;
import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Response.getPatientsByCharacter;
import com.HospitalV2.HospitalV2.models.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImp implements HospitalService {

    @Autowired
    HRepository repository;

    public PatientsListResponse filterByAge(int age) {
        List<Patients> patientsFromDb = repository.findAll();

        List<Patients> filteredList = patientsFromDb.stream().filter(patients -> patients.getAge() > age).collect(Collectors.toList());

        return new PatientsListResponse(filteredList);
    }

    public PatientsListResponse filterByAge2(int age, int age2) {
        List<Patients> patientsFromDb = repository.findAll();

        List<Patients> filteredList = patientsFromDb.stream().filter(patients -> patients.getAge() > age && patients.getAge() < age2).collect(Collectors.toList());

        return new PatientsListResponse(filteredList);
    }

    public getPatientsByCharacter getByCharacter(String ch){
        List<Patients> patientsFromDb2 = repository.findAll();

        List<Patients> filteredList2 = patientsFromDb2.stream().filter(patients -> patients.getFirstName().startsWith(ch)).collect(Collectors.toList());

        return new getPatientsByCharacter(filteredList2);

       // (getByCharacter())).findFirst().orElse(null);
    }

    public Patients savePatient(Map<String, String> body) {
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String ageString = body.get("age");
        int age = Integer.parseInt(ageString);
        return repository.save(new Patients(firstName, lastName, age));

    }

   /* public Patients updatePatient(Patients patientsRequest, Integer id){
        Optional<Patients> patientsO = repository.findById(id);
        if (!patientsO.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            Patients patientToUpdate = patientsO.get();

            patientToUpdate.setAge(patientsRequest.getAge());
            patientToUpdate.setFirstName(patientsRequest.getFirstName());
            patientToUpdate.setLastName((patientsRequest.getLastName()));

           return repository.save(patientsO.get());
        }
    }*/


}
