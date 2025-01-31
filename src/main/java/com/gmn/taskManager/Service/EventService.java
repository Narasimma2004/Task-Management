package com.gmn.taskManager.Service;

import com.gmn.taskManager.Entity.Event;
import com.gmn.taskManager.Repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepo eventRepo;

    public ResponseEntity<?> addevent(Event event) {
        eventRepo.save(event);
      return ResponseEntity.ok("Event added");
    }

    public String deleteEvent(int eventId) {
        if(eventRepo.existsById(eventId)) {
            eventRepo.deleteById(eventId);
            return "Event deleted";
        }
        return "No event Found";
    }

    public List<Event> getallevents() {
        return eventRepo.findAll();
    }
}
