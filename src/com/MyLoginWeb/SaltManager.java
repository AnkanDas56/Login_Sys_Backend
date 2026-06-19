package com.MyLoginWeb;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

public class SaltManager implements PasswordEncoder {
    @Override
    public @Nullable String encode(@Nullable CharSequence rawPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append(rawPassword);
        sb.append(rawPassword.hashCode());
        sb.append(rawPassword.toString().length());
        sb.append(rawPassword.charAt(0));
        sb.append(rawPassword.charAt(1));
        sb.append(rawPassword.charAt(rawPassword.length() - 1));
        return sb.toString();
    }

    @Override
    public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
        return Objects.equals(this.encode(rawPassword), encodedPassword);
    }

}
