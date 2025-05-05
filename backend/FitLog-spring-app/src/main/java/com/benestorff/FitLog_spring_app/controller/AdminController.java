package com.benestorff.FitLog_spring_app.controller;

import com.benestorff.FitLog_spring_app.model.Admin;
import com.benestorff.FitLog_spring_app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Admin create(@RequestBody Admin admin) {
        return adminService.save(admin);
    }

    @GetMapping
    public List<Admin> getAll() {
        return adminService.findAll();
    }
}
