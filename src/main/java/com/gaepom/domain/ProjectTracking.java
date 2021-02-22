package com.gaepom.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "project_tracking")

public class ProjectTracking {

	@Id
	@GeneratedValue
	@Column(name = "track_seq")
	private long trackSeq;

	private String stage;

	private String issue;

	private String output;

	@Column(name = "track_like")
	private int trackLike;

	@Column(name = "track_image", nullable = true)
	private String trackImage;

	@Column(name = "track_date", insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date trackDate;

	@Column(name = "track_link")
	private String trackLink;

	@JsonManagedReference(value = "comment")
	@OneToMany(mappedBy = "trackSeq", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> Tcomments;

	@OneToOne(mappedBy = "trackSeq", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Project project;

}
