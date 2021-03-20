package ge.find.findjob.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestModel {
    @NotBlank(message = "Username cannot be empty")
    public String username;
    @NotBlank(message = "Password cannot be empty")
    public String nickname;
    @NotBlank(message = "Password cannot be empty")
    public String password;
    @NotBlank(message = "Password cannot be empty")
    public String repeatPassword;
    @Email(message = "Email is not correct")
    public String email;

}
