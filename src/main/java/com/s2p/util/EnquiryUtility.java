package com.s2p.util;

import com.s2p.dto.EnquiryDto;
import com.s2p.model.Enquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class EnquiryUtility
{
    public final Enquiry toEnquiryEntity(EnquiryDto enquiryDto)
    {
        Enquiry enquiry = new Enquiry();

        enquiry.setEnquiryId(enquiryDto.getEnquiryId());
        enquiry.setEnquiryDate(enquiryDto.getEnquiryDate());

        return enquiry;
    }

    public final EnquiryDto toEnquiryDto(Enquiry enquiry)
    {
        EnquiryDto enquiryDto = new EnquiryDto();

        enquiryDto.setEnquiryId(enquiry.getEnquiryId());
        enquiryDto.setEnquiryDate(enquiry.getEnquiryDate());

        return enquiryDto;
    }
}
