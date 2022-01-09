package br.com.bearblog.publication.dto;

import br.com.bearblog.publication.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UserDto {

    private String password;
    private String email;

    public User userFactory(){
        User user = new User();
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }

}
