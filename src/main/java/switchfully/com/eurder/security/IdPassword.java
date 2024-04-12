package switchfully.com.eurder.security;

import java.util.UUID;

public class IdPassword {
    private final UUID userId;
    private final String password;
    public IdPassword(UUID userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UUID getUserId() {
        return this.userId;
    }
    public String getPassword() {
        return password;
    }
}
