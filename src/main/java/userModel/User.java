package userModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String email;
    private String password;
    private String name;

    public static User getRandom() {
        String email = RandomStringUtils.randomAlphanumeric(5) + "@.mail.ru";
        String password = RandomStringUtils.randomAlphanumeric(10);
        String name = RandomStringUtils.randomAlphanumeric(10);

        return new User(email, password, name);    }


    public static User getUserWithShortPassword() {
        String email = RandomStringUtils.randomAlphanumeric(5) + "@.mail.ru";
        String password = RandomStringUtils.randomAlphanumeric(5);
        String name = RandomStringUtils.randomAlphanumeric(10);

        return new User(email, password, name);
    }
}
