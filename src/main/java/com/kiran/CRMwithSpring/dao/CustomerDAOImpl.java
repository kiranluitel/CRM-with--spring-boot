package com.kiran.CRMwithSpring.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kiran.CRMwithSpring.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private EntityManager sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.unwrap(Session.class);
				
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer"
						+ " order by lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session currentSession = sessionFactory.unwrap(Session.class);
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession = sessionFactory.unwrap(Session.class);
		
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
				
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		Session currentSession = sessionFactory.unwrap(Session.class);
		
		
		Customer theCustomer = currentSession.get(Customer.class, theId);
				
		currentSession.delete(theCustomer);
		
	}

}






