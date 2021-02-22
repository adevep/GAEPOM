package com.gaepom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Portfolio {

	@Id
	@GeneratedValue
	@Column(name = "pf_seq")
	private Long pfSeq;

	@Column(name = "pf_subtitle")
	private String pfSubtitle;

	@Column(name = "pf_duration")
	private String pfDuration;

	@Column(name = "pf_description", length = 1024)
	private String pfDescription;

	private String participation;

	@Column(name = "pf_position")
	private String pfPosition;

	@Column(name = "pf_lang", nullable = true)
	private String pfLang;

	@Column(name = "pf_tools")
	private String pfTools;

	@Column(name = "pf_dbms", nullable = true)
	private String pfDbms;

	@Column(name = "pf_link", nullable = true)
	private String pfLink;

	@Column(name = "pf_category")
	private String pfCategory;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;

}
