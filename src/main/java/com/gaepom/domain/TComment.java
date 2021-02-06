package com.gaepom.domain;

import java.util.Date;
import java.util.List;

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
public class TComment {
	@Id
	@GeneratedValue
	@Column(name="cmt_seq")
	private Long cmtSeq;
	
	private String cmt;
	
	@Column(name="cmt_date", insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date cmtDate;
	
	// 생성될 때 무조건 값 0 넣기
	@Column(name = "cmt_like")
	private int cmtLike;

	@Column(name = "parent_id", nullable = true)
	private String parentId;
	
	@Column(nullable=true)
	private int depth;
	
	@Column(name="user_id")
	private String userId;
	
	@ManyToOne
	@JoinColumn(name="track_seq")
	private ProjectTracking trackSeq;

}
