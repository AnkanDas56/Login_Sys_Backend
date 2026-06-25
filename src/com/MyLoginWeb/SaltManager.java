package com.MyLoginWeb;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class SaltManager implements PasswordEncoder {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public @Nullable String encode(@Nullable CharSequence rawPassword) {

        StringBuilder sb = new StringBuilder();
        sb.replace(0, rawPassword.length(), this.encoder.encode(rawPassword));
        sb.append(rawPassword.hashCode());
        sb.append(rawPassword.toString().length());
        sb.append(rawPassword.charAt((int) (Math.random() * rawPassword.length())));
        sb.append(rawPassword.charAt(1));
        sb.append(rawPassword.charAt(rawPassword.length() - 1));
        return sb.toString();
    }

    @Override
    public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
        String s = this.removeExtraEncoding(encodedPassword, (String) rawPassword);
        return this.encoder.matches(rawPassword, this.removeExtraEncoding(encodedPassword, (String) rawPassword));
        //return this.encoder.matches(rawPassword,encodedPassword);
    }

    private @NonNull String removeExtraEncoding(String encodedPassword, @Nullable String rawPassword) {
        String sb = String.valueOf(rawPassword.hashCode()) +
                rawPassword.length() +
                rawPassword.charAt((int) (Math.random() * rawPassword.length())) +
                rawPassword.charAt(1) +
                rawPassword.charAt(rawPassword.length() - 1);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(encodedPassword);
        sb2.delete(encodedPassword.length() - sb.length(), encodedPassword.length());
        return sb2.toString();
    }

}
