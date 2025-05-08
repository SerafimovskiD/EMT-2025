package mk.ukim.finki.lab.events;

import mk.ukim.finki.lab.model.domain.Host;
import org.springframework.context.ApplicationEvent;

public class HostDeletedEvent extends ApplicationEvent {
    public HostDeletedEvent(Host source) {
        super(source);
    }
}
