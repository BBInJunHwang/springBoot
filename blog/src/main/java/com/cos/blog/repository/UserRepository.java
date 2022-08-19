package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.UserTB;

// DAO
// 자동으로 빈등록 된다. @Repository 생략 가능
// User 테이블이 관리하는 레포지토리이며, pk는 Integer형이다
public interface UserRepository extends JpaRepository<UserTB, String>{
	
	//JPA naming query 전략 
	// select * from USERTB where username =? and password =?; 
	//UserTB findByUsernameAndPassword(String username, String password);
	
	// 위와 동일하다.
	//@Query(value="select * from USERTB where username =?1 and password =?2", nativeQuery = true)
	//UserTB login(String username, String password);
}
