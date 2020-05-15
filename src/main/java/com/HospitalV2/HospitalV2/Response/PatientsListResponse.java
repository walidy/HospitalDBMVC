package com.HospitalV2.HospitalV2.Response;

import com.HospitalV2.HospitalV2.Domain.Patients;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PatientsListResponse {

    @JsonProperty
    private List<Patients> patientsList;

    public PatientsListResponse(List<Patients> patients){

        this.patientsList = patients;
    }

    public List<Patients> getPatientsList() {
        return patientsList;
    }
}
