package com.benestorff.FitLog_spring_app.repository;

import com.benestorff.FitLog_spring_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
