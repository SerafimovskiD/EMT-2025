//package mk.ukim.finki.lab1.Listeners;
//
//import mk.ukim.finki.lab1.events.HostCreatedEvent;
//import mk.ukim.finki.lab1.events.HostDeletedEvent;
//import mk.ukim.finki.lab1.events.HostUpdatedEvent;
//import mk.ukim.finki.lab1.service.Domain.HostService;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class HostEventHandlers {
//    private final HostService hostService;
//
//    public HostEventHandlers(HostService hostService) {
//        this.hostService = hostService;
//    }
//
//    @EventListener
//    public void onHostCreated(HostCreatedEvent event){
//        this.hostService.refreshMaterializedView();
//    }
//
//    @EventListener
//    public void onHostUpdated(HostUpdatedEvent event){
//        this.hostService.refreshMaterializedView();
//    }
//
//    @EventListener
//    public void onHostDeleted(HostDeletedEvent event){
//        this.hostService.refreshMaterializedView();
//    }
//}
