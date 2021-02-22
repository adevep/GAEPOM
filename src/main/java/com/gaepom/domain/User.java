package com.gaepom.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "g_user")
public class User {

	@Id
	@Column(name = "user_id")
	private String userId;

	@JsonIgnore
	private String password;

	private String name;

	private int age;

	private String email;

	@Column(name = "phone_num")
	private String phoneNum;

	private String address;

	private String stack;

	@Column(name = "user_image", nullable = true)
	private String userImage;

	private String position;

	@Column(name = "t_liked")
	private String tLiked;

	@JsonIgnore
	@OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
	private List<Portfolio> portfolios;

	@JsonIgnore
	@OneToMany(mappedBy = "userId", cascade = CascadeType.REMOVE)
	private List<Project> projects;

}
