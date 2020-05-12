package com.HospitalV2.HospitalV2.Service;

import com.HospitalV2.HospitalV2.Repository.HRepository;
import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Response.getPatientsByCharacter;
import com.HospitalV2.HospitalV2.models.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public getPatientsByCharacter getByCharacter(String ch){
        List<Patients> patientsFromDb2 = repository.findAll();

        List<Patients> filteredList2 = patientsFromDb2.stream().filter(patients -> patients.getFirstName().startsWith(ch)).collect(Collectors.toList());

        return new getPatientsByCharacter(filteredList2);

       // (getByCharacter())).findFirst().orElse(null);
    }


}
