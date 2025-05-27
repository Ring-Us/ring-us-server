package es.princip.ringus.global.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmitterRepository {
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
    @Value("${app.notification.emitter.timeout}")
    private Long TIMEOUT;

    public SseEmitter save(Long receiverId) {
        log.info("Sending message to emitter {}", receiverId);
        log.info("Emitter timeout set to {} ms", TIMEOUT);

        SseEmitter emitter = new SseEmitter(TIMEOUT);
        emitters.put(receiverId, emitter);

        emitter.onCompletion(() -> emitters.remove(receiverId));
        emitter.onTimeout(() -> emitters.remove(receiverId));
        return emitter;
    }

    public Optional<SseEmitter> get(Long receiverId) {
        return Optional.ofNullable(emitters.get(receiverId));
    }

    public void remove(Long receiverId) {
        emitters.remove(receiverId);
    }
}