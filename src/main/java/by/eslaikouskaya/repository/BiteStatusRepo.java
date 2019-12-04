package by.eslaikouskaya.repository;

import by.eslaikouskaya.pojo.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class BiteStatusRepo {

	private static Logger log = Logger.getLogger("BiteStatusRepo");

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(BiteStatus biteStatus) {
			sessionFactory.getCurrentSession().save(biteStatus);
	}

	@Transactional
	public BiteStatus findByType(BiteStatusType type) {
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from BiteStatus where biteStatusType like :param", BiteStatus.class)
					.setParameter("param", type)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}
}
