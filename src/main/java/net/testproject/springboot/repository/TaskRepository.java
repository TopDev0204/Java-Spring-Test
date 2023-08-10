package net.testproject.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.testproject.springboot.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
