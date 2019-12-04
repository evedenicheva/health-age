package by.eslaikouskaya.repository;

import by.eslaikouskaya.pojo.Sex;
import by.eslaikouskaya.pojo.SexType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Repository
public class SexRepo {

	private static Logger log = Logger.getLogger("SexRepo");

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(Sex sex) {
		sessionFactory.getCurrentSession().save(sex);
	}

	@Transactional
	public Sex findByType(SexType type) {
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from Sex where sexType like :param", Sex.class)
					.setParameter("param", type)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}
}
