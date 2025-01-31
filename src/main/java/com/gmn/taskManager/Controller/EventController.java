package com.gmn.taskManager.Controller;

import com.gmn.taskManager.Entity.Event;
import com.gmn.taskManager.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/event")
    public ResponseEntity<?> addEvent(@RequestBody Event event){
       return eventService.addevent(event);
    }
    
    @DeleteMapping("/event/{eventId}")
    public String deleteEvent(@PathVariable("eventId") int eventId){
        return eventService.deleteEvent(eventId);
    }

    @GetMapping("event")
    public List<Event> getAllEvents(){
        return eventService.getallevents();
    }
}
