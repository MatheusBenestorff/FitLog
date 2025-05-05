package com.benestorff.FitLog_spring_app.controller;

import com.benestorff.FitLog_spring_app.model.WorkoutSessionSet;
import com.benestorff.FitLog_spring_app.service.WorkoutSessionSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/session-sets")
public class WorkoutSessionSetController {

    @Autowired
    private WorkoutSessionSetService setService;

    @GetMapping
    public List<WorkoutSessionSet> getAll() {
        return setService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<WorkoutSessionSet> getById(@PathVariable Long id) {
        return setService.findById(id);
    }

    @GetMapping("/session/{sessionId}")
    public List<WorkoutSessionSet> getBySessionId(@PathVariable Long sessionId) {
        return setService.findBySessionId(sessionId);
    }

    @PostMapping
    public WorkoutSessionSet create(@RequestBody WorkoutSessionSet set) {
        return setService.save(set);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        setService.deleteById(id);
    }
}
