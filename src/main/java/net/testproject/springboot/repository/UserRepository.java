package net.testproject.springboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.testproject.springboot.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
    boolean existsByUsername(String username);
}
