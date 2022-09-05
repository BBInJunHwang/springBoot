/*
 * package com.ijhwang.jwt.controller;
 * 
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.ijhwang.jwt.model.UserInfo; import
 * com.ijhwang.jwt.repository.UserRepository;
 * 
 * // @CrossOrigin // 인증이 필요없는 요청에서는 해결이 되지만, 인증 필요 요청시 문제가 된다.
 * 
 * @RestController public class RestApiController {
 * 
 * private UserRepository userRepository; private BCryptPasswordEncoder
 * bCryptPasswordEncoder;
 * 
 * public RestApiController(UserRepository userRepository, BCryptPasswordEncoder
 * bCryptPasswordEncoder) { this.userRepository = userRepository;
 * this.bCryptPasswordEncoder = bCryptPasswordEncoder; }
 * 
 * @GetMapping("home") public String home() { return "<h1>home</h1>"; }
 * 
 * @PostMapping("/api/v1/user/token") public String token() { return
 * "<h1>token</h1>"; }
 * 
 * @PostMapping("join") public String join(@RequestBody UserInfo user) {
 * user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
 * user.setRoles("ROLE_USER"); userRepository.save(user); return "회원가입완료"; }
 * 
 * // admin,manger,user 접근가능
 * 
 * @GetMapping("/api/v1/user") public String user() { return "user"; }
 * 
 * // admin, manager만 가능
 * 
 * @GetMapping("/api/v1/manager") public String manager() { return "manager"; }
 * 
 * // admin만 가능
 * 
 * @GetMapping("/api/v1/admin") public String admin() { return "admin"; } }
 */