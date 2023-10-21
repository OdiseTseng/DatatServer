package tw.intelegence.ncsist.sstp.repo;


//import javax.transaction.Transactional;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.User;


@Repository
public interface UserBackRepository extends JpaRepository<User, Long> {

	//預設提供Optional<User> findById(Long id);

	User findByUsername(String username);

	@Query("select u from User u where u.id <= ?1")
	Page<User> findMore(Long maxId, Pageable pageable);

	@Modifying
	@Transactional
	@Query("update User u set u.name = ?1 where u.id = ?2")
	int updateById(String name, Long id);

}
