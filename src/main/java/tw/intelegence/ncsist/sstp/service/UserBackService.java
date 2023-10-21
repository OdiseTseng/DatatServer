package tw.intelegence.ncsist.sstp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.User;
import tw.intelegence.ncsist.sstp.repo.UserBackRepository;
import tw.intelegence.ncsist.sstp.utils.func.CommonFunction;

import java.util.List;

@Service("userBackService")
public class UserBackService {
	@Autowired
	private UserBackRepository userRepository;

	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	public User getUserByID(Long id){
		return userRepository.findById(id).get();
	}

	public User getByUsername(String name){
		return userRepository.findByUsername(name);
	}

	public Page<User> findPage(){
		Pageable pageable = PageRequest.of(0, 10);
		return userRepository.findAll(pageable);
	}

	public Page<User> find(Long maxId){
		Pageable pageable = PageRequest.of(0, 10);
		return userRepository.findMore(maxId,pageable);
	}

	public User save(User u){
		return userRepository.save(u);
	}

	public User update(Long id, String pass){
		User user = userRepository.findById(id).get();
		pass = CommonFunction.encrypt( pass );
		user.setPassword(pass);
		return userRepository.save(user);
	}

	public Boolean updateById(String  name, Long id){
		return userRepository.updateById(name,id)==1;
	}


	public User findBy(String username, String password){
//		return queryFactory.selectFrom(QUser.user).where()
		return null;
	}

}
