package com.gaepom.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="project_tracking")
public class ProjectTracking {
	
	@Id
	@GeneratedValue
	@Column(name="track_seq")
	private Long trackSeq;
	
	private String stage;
	
	private String issue;
	
	@Column(name="track_like")
	private int trackLike;
	
	@Column(name="track_image")
	private String trackImage;
	
	@Column(name="track_date")
	private int trackDate;
	
	@Column(name="track_link")
	private String tackLink;
	
	@OneToMany(mappedBy="trackSeq")
	private List<Comment> Tcomments;
	
	@OneToOne(mappedBy="trackSeq")
	private Project project;

}
