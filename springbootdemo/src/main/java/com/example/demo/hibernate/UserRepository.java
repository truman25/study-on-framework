package com.example.demo.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	public User findByNickname(String nickname);
}
