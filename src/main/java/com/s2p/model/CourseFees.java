package com.s2p.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * File Name: CourseFees.java
 * Entity: CourseFees
 * Package: com.s2p.model
 * Author: pranayramteke
 * Date: 19/08/25
 * Description:
 */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseFees extends BaseEntity implements Serializable
{
	private UUID courseFeesID;
	private Long transactionId;
	private Double courseFees;

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		CourseFees that = (CourseFees) o;
		return Objects.equals(courseFeesID, that.courseFeesID) && Objects.equals(transactionId, that.transactionId) && Objects.equals(courseFees, that.courseFees);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseFeesID, transactionId, courseFees);
	}
}