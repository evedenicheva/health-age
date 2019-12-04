package by.eslaikouskaya.repository;

import by.eslaikouskaya.pojo.BiteStatus;
import by.eslaikouskaya.pojo.BiteStatusType;
import by.eslaikouskaya.pojo.BreakfastStatus;
import by.eslaikouskaya.pojo.BreakfastStatusType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class BreakfastStatusRepo {

	private static Logger log = Logger.getLogger("BreakfastStatusRepo");

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(BreakfastStatus breakfastStatus) {
			sessionFactory.getCurrentSession().save(breakfastStatus);
	}

	@Transactional
	public BreakfastStatus findByType(BreakfastStatusType type) {
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from BreakfastStatus where breakfastStatusType like :param", BreakfastStatus.class)
					.setParameter("param", type)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}
}
