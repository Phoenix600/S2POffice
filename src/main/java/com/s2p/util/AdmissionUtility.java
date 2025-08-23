package com.s2p.util;

import com.s2p.dto.AdmissionDto;
import com.s2p.model.Admission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class AdmissionUtility
{
    public final static Admission toAdmissionEntity(AdmissionDto admissionDto)
    {
        Admission admission = new Admission();

        admission.setAdmissionId(admissionDto.getAdmissionId());
        admission.setAdmissionDate(admissionDto.getAdmissionDate());

        return admission;
    }

    public final static AdmissionDto toAdmissionDto(Admission admission)
    {
        AdmissionDto admissionDto = new AdmissionDto();

        admissionDto.setAdmissionId(admission.getAdmissionId());
        admissionDto.setAdmissionDate(admission.getAdmissionDate());

        return admissionDto;
    }
}
