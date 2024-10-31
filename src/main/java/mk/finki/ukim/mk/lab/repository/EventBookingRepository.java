package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventBookingRepository {
    private static List<EventBooking> eventBookings;
    public EventBookingRepository(){
        this.eventBookings = new ArrayList<>();
    }
    public List<EventBooking> findAll(){
        return eventBookings;
    }
    public List<EventBooking> searchByAttendee(String name){
        return eventBookings
                .stream()
                .filter(eb -> eb.getAttendeeName().equals(name))
                .collect(Collectors.toList());
    }
    public List<EventBooking> searchByEvent(String name){
        return eventBookings
                .stream()
                .filter(eb -> eb.getEventName().equals(name))
                .collect(Collectors.toList());
    }
    public EventBooking addOrUpdate(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets){
        eventBookings.removeIf(eb -> eb.getEventName().equals(eventName) && eb.getAttendeeName().equals(attendeeName));
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        eventBookings.add(eventBooking);

        return eventBooking;
    }
}
