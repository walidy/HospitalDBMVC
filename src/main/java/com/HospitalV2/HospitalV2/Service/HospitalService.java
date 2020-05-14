package com.HospitalV2.HospitalV2.Service;

import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Response.getPatientsByCharacter;
import com.HospitalV2.HospitalV2.models.Patients;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface HospitalService {

     PatientsListResponse filterByAge(int age);

       getPatientsByCharacter getByCharacter(String ch);

    PatientsListResponse filterByAge2(int age, int age2);

    Patients savePatient(Map<String, String> body);
}
