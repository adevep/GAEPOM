package com.gaepom.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "project")

@Entity
@Table(name = "project_recruit")
public class ProjectRecruit {

	@Id
	@GeneratedValue
	private Long recSeq;

	@Column(name = "need_num")
	private int needNum;

	@Column(name = "need_posi")
	private String needPosi;

	private String location;

	private String preference;

	@Column(name = "rec_status")
	private int recStatus;

	@Column(name = "rec_duration")
	private String recDuration;

	@Column(name = "rec_date", insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date recDate;

	@Column(columnDefinition = "number(10) default 0")
	private int checkCount;

	@JsonIgnore
	@OneToOne(mappedBy = "recSeq")
	private Project project;

}
