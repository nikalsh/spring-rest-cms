package se.nackademin.restcms.payload;

import lombok.Data;

@Data
public class UserCreatedResponse {
private Long id;
private String email;
private String name;

public UserCreatedResponse (Long id, String email, String name) {
	this.id = id;
	this.email = email;
	this.name = name;
}
}
