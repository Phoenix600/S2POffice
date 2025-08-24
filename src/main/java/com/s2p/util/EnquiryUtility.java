package com.s2p.util;

import com.s2p.dto.EnquiryDto;
import com.s2p.model.Enquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnquiryUtility
{
    public abstract Enquiry toEnquiryEntity(EnquiryDto enquiryDto);
    public abstract EnquiryDto toEnquiryDto(Enquiry enquiry);
}
