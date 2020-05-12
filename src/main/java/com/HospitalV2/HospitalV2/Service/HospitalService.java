package com.HospitalV2.HospitalV2.Service;

import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Response.getPatientsByCharacter;
import org.springframework.stereotype.Service;


public interface HospitalService {

     PatientsListResponse filterByAge(int age);

       getPatientsByCharacter getByCharacter(String ch);
}
