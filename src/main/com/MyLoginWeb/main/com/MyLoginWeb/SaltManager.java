package main.com.MyLoginWeb;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SaltManager implements PasswordEncoder {
    @Override
    public @Nullable String encode(@Nullable CharSequence rawPassword) {
    return rawPassword.toString()+Integer.toString(rawPassword.length())+rawPassword.charAt(0);
    }

    @Override
    public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
        return this.encode(rawPassword).hashCode()==encodedPassword.hashCode();
    }

}
