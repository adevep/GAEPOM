package com.gaepom.dao;

import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, String> {

}
