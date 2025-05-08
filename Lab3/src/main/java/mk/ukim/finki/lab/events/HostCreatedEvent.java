package mk.ukim.finki.lab.events;


import mk.ukim.finki.lab.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class HostCreatedEvent extends ApplicationEvent {
    private LocalDateTime when;
    public HostCreatedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public HostCreatedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when=when;

    }
}
