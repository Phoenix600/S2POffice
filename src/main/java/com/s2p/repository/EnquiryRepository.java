package com.s2p.repository;

import com.s2p.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry,UUID>
{

}
