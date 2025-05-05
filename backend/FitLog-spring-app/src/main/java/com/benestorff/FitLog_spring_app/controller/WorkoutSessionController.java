package com.benestorff.FitLog_spring_app.controller;

import com.benestorff.FitLog_spring_app.model.WorkoutSession;
import com.benestorff.FitLog_spring_app.service.WorkoutSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout-sessions")
public class WorkoutSessionController {

    @Autowired
    private WorkoutSessionService sessionService;

    @PostMapping("/start/{workoutId}")
    public WorkoutSession startSession(@PathVariable Long workoutId) {
        return sessionService.startSession(workoutId);
    }

    @PostMapping("/end/{sessionId}")
    public WorkoutSession endSession(@PathVariable Long sessionId) {
        return sessionService.endSession(sessionId);
    }
}
