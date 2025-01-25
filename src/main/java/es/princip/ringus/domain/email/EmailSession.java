package es.princip.ringus.domain.email;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@RedisHash(value = "EmailSession", timeToLive = 1800) // TTL: 30분
public class EmailSession implements Serializable {

    @Id
    private String email;

    public static EmailSession of(String email) {
        return new EmailSession(email);
    }

    private EmailSession(String email) {
        this.email = email;
    }
}