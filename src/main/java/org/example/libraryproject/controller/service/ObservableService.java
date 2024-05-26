package org.example.libraryproject.controller.service;

import org.example.libraryproject.controller.dto.NotifyType;
import org.example.libraryproject.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ObservableService {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    /**
     * creates an SSE connection for the current user and returns its SSE emitter
     */
    public SseEmitter addObserver() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        User currentUser =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        emitters.put(currentUser.getUsername(),emitter);
        emitter.onCompletion(() -> emitters.remove(currentUser.getUsername()));
        emitter.onTimeout(() -> emitters.remove(currentUser.getUsername()));
        emitter.onError((e) -> emitters.remove(currentUser.getUsername()));
        return emitter;
    }

    /**
     * removes the currently signed user's SSE emitter
     */
    public void removeObserver(){
        User currentUser =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        emitters.remove(currentUser.getUsername());
    }

    private static int defaultThreadsNo =5;

    /**
     * notify all the SSE emitters with the NotifyType specified
     * remove an emitter if there are errors on connection
     */
    public void notifyObservers(NotifyType type) {
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        List<SseEmitter> deadEmitters = new CopyOnWriteArrayList<>();
        for (SseEmitter emitter : emitters.values()){
                executor.execute(() -> {
                    try {
                        emitter.send(SseEmitter.event().name("update").data(type.toString()));
//                        System.out.println("update message sent to client");
                    } catch (IOException e) {
                        deadEmitters.add(emitter);
//                        System.out.println("dead emitter added");
                    }
                });
        }
        for(SseEmitter em : deadEmitters){
            emitters.values().remove(em);
        }
        executor.shutdown();
    }
}