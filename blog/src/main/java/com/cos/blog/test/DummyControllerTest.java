package com.cos.blog.test;


import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.UserTB;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입 해당 컨트롤러가 메모리에 뜰때 userRepository도 같이 띄운다. 
	private UserRepository userRepository;
	
	// http://localhost:8000/blog/dummy/join
	// http body에 username password email 요청
	@PostMapping("/dummy/join")
	public String join(String username,String password, String email) {
		
		System.out.println("dummy controller");
		return "회원가입이 완료되었습니다";
		
	}
	
	// http://localhost:8000/blog/dummy/join
	// http body에 username password email 요청
	// User 객체로 파싱 가능
	@PostMapping("/dummy/join2")
	public String join(UserTB user) {
		System.out.println("dummy controller");
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());		
		
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다";
		
	}
	
	// http://localhost:8000/blog/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public UserTB detail(@PathVariable String username) {
		
		// ex) 4 유저가 없을때
		// user/4를 찾을때 DB에서 못찾으면 user가 null 이된다.
		// 그럼 return null 인한 에러 발생?
		// Optional로 User 객체를 감싸서 가져올테니 null 인지 판단해서 return 해라
		
		//User user = userRepository.findById(id).get(); // .get() 그냥 객체 그대로 뽑아온다. null 체크 안함
		
		
		/*
		 * User user = userRepository.findById(id).orElseGet(new Supplier<User>() { //
		 * .orElseGet() 없을때 겍체 만들어서 넣어줘라
		 * 
		 * @Override public User get() { // TODO Auto-generated method stub 
		 * return new User(); } });
		 */
		
		// orElseThrow 없을땐 에러를 던져라 
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//			// 인터페이스, 추상메서드는 new 생성 불가능, 익명 클래스 만들어서 오버라이드 해줘야함
//			@Override
//			public IllegalArgumentException get() {
//				// TODO Auto-generated method stub
//				return new IllegalArgumentException("해당 유저는 없습니다.");
//			}
//		});
		
		// 람다식 사용
		UserTB user = userRepository.findById(username).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다.");
		});
		
		// 요청은 웹브라우저에서 했음.. @RestController 이기 때문에 html 파일이 아닌 data를 리턴한다
		// user 객체 = 자바 오브젝트 -> 웹브라우저는 오브젝트 이해못함, html css js만 이해가능
		// user 객체를 리턴할때 웹 브라우저가 이해할 수 있는 데이터로 변환해야함 -> json 데이터 변환
		// 스프링 부트는 = MessageConverter 라는 애가 응답시 자동작동함
		// 만약 자바 오브젝트가 리턴 시 MessageConverter가 Jackson lib 호출 해서 User Ojbect -> json 변환해서 브라우저에 던져줌
		// Content-Type 찍어보면 application/json 되어있음 
		return user;
		
	}
	
	// http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<UserTB> list(){
		return userRepository.findAll();
		
	}
	
	// http://localhost:8000/blog/dummy/user/page 기본값
	//http://localhost:8000/blog/dummy/user?page=2 ?page=값으로 쿼리스트링으로 1,2,3..페이지 요청가능
	// 한페이지당 2건의 데이터를 리턴
	@GetMapping("/dummy/user")  // 2건, id로 Desc 정렬
	public List<UserTB> pagingList(@PageableDefault(size=2, sort ="id", direction = Sort.Direction.DESC) Pageable pageable){
	
		Page<UserTB> pagingUser = userRepository.findAll(pageable); // 각종 기본 page안에 포함된 다른값들이 모두나옴
		//List<User> users = userRepository.findAll(pageable).getContent(); // content부분만 리턴
		
		// 첫번쨰 데이터인지?
		if(pagingUser.isFirst()) {
			
		// 마지막 데이터인지?
		}else if(pagingUser.isLast()) {
			
		}
		
		List<UserTB> users = pagingUser.getContent();
		
		return users;		
	}
	
	// GetMapping 이랑 같아도 알아서 구분한다.
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public UserTB updateUser(@PathVariable String username, @RequestBody UserTB requestUser) { // @RequestBody json 데이터를 요청 -> java Object MessageConverter 의 jackson lib 가 변환해서 받아줌
		
		System.out.println("username : " + username);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		
		//JPA 에서는 update 하기 위해서는 select 후 save
		// save 함수는 id를 전달하지 않으면 insert 해주고
		// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update 해주고
		// save 함수는 id를 전달하면 해당 id에 대핟 데이터가 없으면 insert 한다.
		
		// 자바는 파라미터에 함수를 넣어줄수 없다. 무조건 객체..
		UserTB user = userRepository.findById(username).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");

		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		// @Transactional 이용하면 더티체킹 한다.
		/**
		 * JPA 안에 영속성 컨텍스트가 존재함
		 * if save 
		 * 1차 캐시 -> user 객체가 쌓임 -> DB에 save 실시
		 * 1차 캐시 안에 존재 -> 영속화 되다.
		 * 1차 캐시 -> DB에 밀어넣으면 flush 된다. but 캐시를 지우지 않는다.
		 * 
		 * if select
		 * 1차 캐시 -> user 가 존재하면 DB 접근 없이 캐시 데이터 들고옴, 부하 적어짐
		 * 
		 * if update  2번 데이터
		 * DB에서 2번 데이터를 찾은 후 영속화 시킨다.
		 * 컨트롤러에서 2번 데이터를 파라미터에 받은 값으로 set 후 save 시키면 
		 * 1차 캐시에 아까 찾았던 2번데이터가 이미 영속되어있어서(이미 존재), 변경된 값을 flush 한다.
		 * 
		 * @Transactional 어노테이션이 존재하면, 컨트롤러 메서드가 호출시 시작되며
		 * 컨트롤러의 updateUser 함수가 종료되면(return 할때), 함수 종료시 자동 commit 된다.
		 * 
		 * 단순 데이터 select 후 찾은 값을 set만 해줬는데 어떻게 update가 되냐?
		 * 
		 * 2번 데이터 수정 
		 * 1. select 시 DB에서 2번데이터를 DB에서 찾은 후 영속성 컨텍스트에 가져온다.
		 * 2. 가져온 값을 set 파라미터값으로 변경한다.
		 * 3. 컨트롤러 종료 -> 트랜잭션 커밋됨 -> 영속화된 object와 set으로 변경된 데이터를 비교 후 DB에 밀어 넣는다.
		 * -> 트랜잭션 종료시 영속화 데이터와 set으로 바뀐 데이터 비교 후 변경 감지 -> update
		 * 
		 * 만약 set으로 값을 변경해주지 않는다면, 영속화된 object 변경값이 없는걸 감지해서 아무동작하지 않는다.
		 * */ 
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable String username) {
		
		try {
			userRepository.deleteById(username);
			
			// Exception 걸어도 괜찮다. 모든 exception의 부모이기 떄문에
			// 하지만 다른 exception에도 걸리기 때문에 정확한 에러 처리를 위해서 정확한 에러 넣자
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. : username" + username;
	}

	
}
