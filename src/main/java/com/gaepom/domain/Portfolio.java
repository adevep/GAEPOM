package com.gaepom.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Portfolio {
	
	@Id
	@GeneratedValue
	@Column(name="pf_seq")
	private Long pfSeq;
	
	@Column(name="pf_subtitle")
	private String pfSubtitle;
	
	@Column(name="pf_duration")
	private String pfDuration;
	
	@Column(name="pf_description")
	private String pfDescription;
	
	private String participation;
	
	@Column(name="pf_lang")
	private String pfLang;
	
	@Column(name="pf_tools")
	private String pfTools;
	
	@Column(name="pf_dbms")
	private String pfDbms;
	
	@Column(name="pf_link", nullable=true)
	private String pfLink;
	
	@Column(name="pf_category")
	private String pfCategory;
	
//	@OneToMany
	@ManyToOne
	@JoinColumn(name="user_id", nullable=true)
	private User userId;
}