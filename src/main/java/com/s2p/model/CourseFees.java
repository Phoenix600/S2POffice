package com.s2p.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
@EqualsAndHashCode
public class CourseFees extends BaseEntity implements Serializable
{
	private UUID courseFeesID;
	private Long transactionId;
	private Double courseFees;
}