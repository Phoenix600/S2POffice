package com.s2p.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity
{
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@LastModifiedBy
	@Column(insertable = false)
	private String updatedBy;
	
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime updatedAt;
}