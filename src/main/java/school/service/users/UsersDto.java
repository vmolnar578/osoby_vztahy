package school.service.users;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UsersDto {
    private Long id;
    private String username;
    private String passwordHash;

}