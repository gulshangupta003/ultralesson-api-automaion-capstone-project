package models.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestBody {
	private String password;
	private String email;
}