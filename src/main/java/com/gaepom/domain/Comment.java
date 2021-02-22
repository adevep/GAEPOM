package com.gaepom.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "t_comment")
public class Comment {

	@Id
	@GeneratedValue
	@Column(name = "cmt_seq")
	private Long cmtSeq;

	private String cmt;

	@Column(name = "cmt_date", insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date cmtDate;

	@Column(name = "cmt_like")
	private int cmtLike;

	@Column(name = "parent_id", nullable = true)
	private String parentId;

	@Column(nullable = true)
	private int depth;

	@Column(name = "user_id")
	private String userId;

	@ManyToOne
	@JsonBackReference(value = "comment")
	@JoinColumn(name = "track_seq", nullable = true)
	private ProjectTracking trackSeq;

}
