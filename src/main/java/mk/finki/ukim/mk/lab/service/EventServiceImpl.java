package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvent(text);
    }

    @Override
    public List<Event> searchByRating(String popularityScore) {
        return eventRepository.searchByRating(Double.parseDouble(popularityScore));
    }
}
