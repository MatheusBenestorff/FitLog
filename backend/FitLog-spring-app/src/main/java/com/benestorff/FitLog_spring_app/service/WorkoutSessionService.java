package com.benestorff.FitLog_spring_app.service;

import com.benestorff.FitLog_spring_app.model.Workout;
import com.benestorff.FitLog_spring_app.model.WorkoutSession;
import com.benestorff.FitLog_spring_app.repository.WorkoutRepository;
import com.benestorff.FitLog_spring_app.repository.WorkoutSessionRepository;
import com.benestorff.FitLog_spring_app.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkoutSessionService {

    @Autowired
    private WorkoutSessionRepository sessionRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public WorkoutSession startSession(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new ResourceNotFoundException("Treino não encontrado"));

        WorkoutSession session = new WorkoutSession();
        session.setWorkout(workout);
        session.setStartTime(LocalDateTime.now());
        return sessionRepository.save(session);
    }

    public WorkoutSession endSession(Long sessionId) {
        WorkoutSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Sessão não encontrada"));

        session.setEndTime(LocalDateTime.now());
        return sessionRepository.save(session);
    }
}
