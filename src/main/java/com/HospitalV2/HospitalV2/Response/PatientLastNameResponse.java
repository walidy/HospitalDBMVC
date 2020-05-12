package com.HospitalV2.HospitalV2.Response;

import com.HospitalV2.HospitalV2.models.Patients;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientLastNameResponse {

    @JsonProperty(value = "Hola")
    private String name;

    @JsonProperty
    private int age;

    public PatientLastNameResponse(Patients patients) {
        this.name = patients.getFirstName();
        this.age = patients.getAge();
    }
}
