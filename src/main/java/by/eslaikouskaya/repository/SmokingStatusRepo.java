package by.eslaikouskaya.repository;

import by.eslaikouskaya.pojo.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class SmokingStatusRepo {

	private static Logger log = Logger.getLogger("SmokingStatusRepo");

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(SmokingStatus smokingStatus) {
		sessionFactory.getCurrentSession().save(smokingStatus);
	}

	@Transactional
	public SmokingStatus findByType(SmokingStatusType type) {
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from SmokingStatus where smokingStatusType like :param", SmokingStatus.class)
					.setParameter("param", type)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}
}
