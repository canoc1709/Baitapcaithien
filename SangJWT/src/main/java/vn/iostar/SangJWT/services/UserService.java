package vn.iostar.SangJWT.services;

import java.util.ArrayList;
import java.util.List;

import vn.iostar.SangJWT.entity.User;
import vn.iostar.SangJWT.repository.UserRepository;

public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> allUsers(){
		List<User> users = new ArrayList<>();
		
		userRepository.findAll().forEach(users::add);
		return users;
	}
}
