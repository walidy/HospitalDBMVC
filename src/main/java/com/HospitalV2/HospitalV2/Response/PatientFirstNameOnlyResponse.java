package com.HospitalV2.HospitalV2.Response;

import com.HospitalV2.HospitalV2.Domain.Patients;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientFirstNameOnlyResponse {

    @JsonProperty
    private String name;



    public PatientFirstNameOnlyResponse(Patients patients) {
        this.name = patients.getFirstName();

    }

}
