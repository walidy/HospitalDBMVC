package com.HospitalV2.HospitalV2.Service;

import com.HospitalV2.HospitalV2.Response.PatientFirstNameOnlyResponse;
import com.HospitalV2.HospitalV2.Response.PatientLastNameResponse;
import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Response.GetPatientsByCharacterResponse;
import com.HospitalV2.HospitalV2.Domain.Patients;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface HospitalService {

    PatientsListResponse filterByAge(int age);

    ResponseEntity<GetPatientsByCharacterResponse> getByCharacter(String ch);

    PatientsListResponse filterByAge2(int age, int age2);

    ResponseEntity<Patients> savePatient(Map<String, String> body);

    ResponseEntity<Patients> updatePatient(Patients patientsRequest, Integer id);

//    ResponseEntity<GetPatientsByCharacterResponse> getPatientByCh(String ch);

    ResponseEntity<PatientFirstNameOnlyResponse> getByFirstNameOnly(String ch);

    ResponseEntity<Patients> deletePatient(Integer Id);

    ResponseEntity<PatientLastNameResponse> getPatientWithoutLastName(Integer id);

    ResponseEntity<PatientsListResponse> getPatientsBetweenAge(Integer age, Integer age2);

    ResponseEntity<PatientsListResponse> getAgeGreaterThan(Integer age);

    ResponseEntity<Patients> getPatientById(Integer id);
}
