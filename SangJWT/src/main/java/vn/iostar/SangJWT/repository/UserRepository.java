package vn.iostar.SangJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iostar.SangJWT.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);	
}
