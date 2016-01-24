package com.gmail.unmacaque.spring.angularjs;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HsqlTaskRepository implements TaskRepository {

	private final SessionFactory sessionFactory;

	@Autowired
	public HsqlTaskRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Task> getTasks() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Task")
				.list();
	}

	@Override
	public Task getTask(int taskId) {
		return (Task) sessionFactory.getCurrentSession()
				.createQuery("from Task task where task.id=:id")
				.setParameter("id", taskId)
				.uniqueResult();
	}

	@Override
	public void addTask(Task task) {
		sessionFactory.getCurrentSession().save(task);
	}

	@Override
	public void updateTask(Task task) {
		sessionFactory.getCurrentSession().saveOrUpdate(task);
	}

	@Override
	public void deleteTask(Task task) {
		sessionFactory.getCurrentSession().delete(task);
	}

}
