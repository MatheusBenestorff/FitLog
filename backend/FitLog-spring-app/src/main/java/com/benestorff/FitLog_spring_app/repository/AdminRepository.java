package com.benestorff.FitLog_spring_app.repository;

import com.benestorff.FitLog_spring_app.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
