package com.tasks.api.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.tasks.api.database.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>, QuerydslPredicateExecutor<Task>, FilterTaskRepository {

}
