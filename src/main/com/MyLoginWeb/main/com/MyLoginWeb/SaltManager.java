package main.com.MyLoginWeb;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SaltManager implements PasswordEncoder {
    @Override
    public @Nullable String encode(@Nullable CharSequence rawPassword) {
    return null;
    }

    @Override
    public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
        return false;
    }

}
