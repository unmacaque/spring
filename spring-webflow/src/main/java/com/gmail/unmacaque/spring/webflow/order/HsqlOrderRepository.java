package com.gmail.unmacaque.spring.webflow.order;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HsqlOrderRepository implements OrderRepository {

	private final SessionFactory sessionFactory;

	@Autowired
	public HsqlOrderRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Order getOrder(long orderId) {
		return (Order) sessionFactory.getCurrentSession()
				.createQuery("from Order where Order.id=?")
				.setParameter(0, orderId)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Order> getOrders() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Order")
				.list();
	}

	@Override
	public void insertOrder(Order order) {
		sessionFactory.getCurrentSession().save(order);
	}

	@Override
	public void removeOrder(Order order) {
		sessionFactory.getCurrentSession().delete(order);
	}

	@Override
	public void updateOrder(Order order) {
		sessionFactory.getCurrentSession().saveOrUpdate(order);
	}

}
