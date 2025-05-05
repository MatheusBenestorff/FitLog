package com.benestorff.FitLog_spring_app.service;

import com.benestorff.FitLog_spring_app.model.WorkoutSessionSet;
import com.benestorff.FitLog_spring_app.repository.WorkoutSessionSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutSessionSetService {

    @Autowired
    private WorkoutSessionSetRepository setRepository;

    public List<WorkoutSessionSet> findAll() {
        return setRepository.findAll();
    }

    public Optional<WorkoutSessionSet> findById(Long id) {
        return setRepository.findById(id);
    }

    public WorkoutSessionSet save(WorkoutSessionSet set) {
        return setRepository.save(set);
    }

    public void deleteById(Long id) {
        setRepository.deleteById(id);
    }

    public List<WorkoutSessionSet> findBySessionId(Long sessionId) {
        return setRepository.findByWorkoutSessionId(sessionId);
    }
}
