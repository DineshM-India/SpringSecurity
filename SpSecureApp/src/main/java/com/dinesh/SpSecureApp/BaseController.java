package com.dinesh.SpSecureApp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
@GetMapping("/")
public String commonAll() {
	return "Hello World!";	
}
@GetMapping("/admin")
//@PreAuthorize("ADMIN")

public String adminUser() {	
    return "Hello Admin!";
}
@GetMapping("/user")
//@PreAuthorize("USERS")
public String appUser() {
	return "Hello User!";
}
}
