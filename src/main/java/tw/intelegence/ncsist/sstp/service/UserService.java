package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.User;
import tw.intelegence.ncsist.sstp.model.UserDTO;
import tw.intelegence.ncsist.sstp.repo.UserRepository;

import java.util.List;

@Service("userService")
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User updatePassword(UserDTO userDTO){
		return userRepository.savePasswordByUsername(userDTO);
	}

	public User getByUsernameAndPassword(String username, String password){
		return userRepository.findUserByUsernameAndPassword(username, password);
	}

	public List<User> getAllUsers(){
		return userRepository.findAllUsers();
	}

	@Transactional
	public User updateUser(User user){

		return userRepository.saveUser(user);
	}

	@Transactional
	public Long deleteUser(User user){

		return userRepository.deleteUser(user.getUsername());
	}

}
