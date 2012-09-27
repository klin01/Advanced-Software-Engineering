package com.ase.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ase.domain.User;

public interface UserService {
	public User findById(long id);

	public List<User> findAll();

	public void save(User customer);

	public void update(User customer);

	public void delete(User customer);

	public void shutdown();
}