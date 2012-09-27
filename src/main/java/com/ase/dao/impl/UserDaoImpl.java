package com.ase.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.ase.dao.UserDao;
import com.ase.domain.User;

@Transactional
@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	@Override
	public User findById(long id) {
		return (User) getSessionFactory().getCurrentSession().get(User.class, id);
	}

	@Override
	public List<User> findAll() {
		Session s = getSessionFactory().openSession();
		List<User> l = s.createQuery("from com.ase.domain.User").list();
		s.close();
		return l;
		
	}

	@Override
	public void save(User user) {
		Session s = getSessionFactory().openSession();
		s.save(user);
		s.close();
	}

	@Override
	public void update(User user) {
		getSessionFactory().getCurrentSession().update(user);
	}

	@Override
	public void delete(User user) {
		getSessionFactory().getCurrentSession().delete(user);
	}

	@Override
	public void shutdown() {
		getSessionFactory().getCurrentSession().createSQLQuery("SHUTDOWN").executeUpdate();
	}

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}