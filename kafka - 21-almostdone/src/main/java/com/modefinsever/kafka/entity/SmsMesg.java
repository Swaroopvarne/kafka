package com.modefinsever.kafka.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sms_mesg", schema = "mfmbs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsMesg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "MTOADDR")
	private String toAddress;

	@Column(name = "MMESSAGE", length = 1000)
	private String message;

	@Column(name = "RSTATUS")
	private int status; // 0 - pending, 1 - success, 2 - failed

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "MODIFIED_AT")
	private LocalDateTime modifiedAt;
}
