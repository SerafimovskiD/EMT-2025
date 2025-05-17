package mk.ukim.finki.lab.events;

import mk.ukim.finki.lab.model.domain.Host;
import org.springframework.context.ApplicationEvent;

public class HostUpdatedEvent extends ApplicationEvent {

    public HostUpdatedEvent(Host source) {
        super(source);
    }
}
