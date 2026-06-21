package com.MyLoginWeb;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

class SaltManager implements PasswordEncoder {
    StringBuilder sb = new StringBuilder();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public @Nullable String encode(@Nullable CharSequence rawPassword) {
        sb.replace(0,rawPassword.length(),encoder.encode(rawPassword));
        sb.append(rawPassword.hashCode());
        sb.append(rawPassword.toString().length());
        sb.append(rawPassword.charAt((int) Math.random()*rawPassword.length()));
        sb.append(rawPassword.charAt(1));
        sb.append(rawPassword.charAt(rawPassword.length() - 1));
        return sb.toString();
    }

    @Override
    public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
        return Objects.equals(this.encode(rawPassword), encodedPassword);
    }

}
