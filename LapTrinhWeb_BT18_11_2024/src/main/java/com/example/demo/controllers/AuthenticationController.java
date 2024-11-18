package vn.iotstar.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	private final JwtService jwtService;
	private final AuthenticationService authenticationService;

	public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<User> register(@RequestBody RegisterUserModel registerUser) {
		User registeredUser = authenticationService.signup(registerUser);
		return ResponseEntity.ok(registeredUser);
	}

	@PostMapping(path = "/login")
	@Transactional
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserModel loginUser) {
		User authenticatedUser = authenticationService.authenticate(loginUser);
		String jwtToken = jwtService.generateToken(authenticatedUser);
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getExpirationTime());
		return ResponseEntity.ok(loginResponse);
	}
	
	@RequestMapping("/users")
	@RestController
	public class UserController {
		private final UserService userService;
		public UserController(UserService userService) {
			this.userService = userService;
		}
		@GetMapping("/me")
		public ResponseEntity<User> authenticatedUser() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User currentUser = (User) authentication.getPrincipal();
			return ResponseEntity.ok(currentUser);
		}
		@GetMapping("/")
		public ResponseEntity<List<User>> allUsers() {
			List<User> users = userService.allUsers();
			return ResponseEntity.ok(users);
		}
	}
	
}