package com.HospitalV2.HospitalV2.Response;

import com.HospitalV2.HospitalV2.models.Patients;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class getPatientsByCharacter {

    @JsonProperty
    private List<Patients> patients;

    public getPatientsByCharacter(List<Patients> patients) {
        this.patients = patients;
    }

    public List<Patients> getPatients() {
        return patients;
    }
}
