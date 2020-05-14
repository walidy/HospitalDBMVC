package com.HospitalV2.HospitalV2.Response;

import com.HospitalV2.HospitalV2.models.Patients;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientFirstNameOnly {

    @JsonProperty
    private String name;



    public PatientFirstNameOnly(Patients patients) {
        this.name = patients.getFirstName();

    }

}
