package com.gaepom.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Application {
	@Id
	@GeneratedValue
	@Column(name = "apl_seq")
	private Long aplSeq;

	@Column(name = "user_id")
	private String userId;

	private String words;

	@Column(name = "apl_posi")
	private String aplPosi;

	private int selected;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "apl_date", insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date aplDate;

	@JsonIgnoreProperties(value = { "recSeq", "trackSeq", "userId", "applications" })
	@ManyToOne
	@JoinColumn(name = "pj_seq", nullable = false)
	private Project pjSeq;
}
