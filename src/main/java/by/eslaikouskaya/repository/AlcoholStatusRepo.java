package by.eslaikouskaya.repository;

import by.eslaikouskaya.pojo.AlcoholStatus;
import by.eslaikouskaya.pojo.AlcoholStatusType;
import by.eslaikouskaya.pojo.Sex;
import by.eslaikouskaya.pojo.SexType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AlcoholStatusRepo {

	private static Logger log = Logger.getLogger("AlcoholStatusRepo");

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(AlcoholStatus alcoholStatus) {
		sessionFactory.getCurrentSession().save(alcoholStatus);
	}

	@Transactional
	public AlcoholStatus findByType(AlcoholStatusType type) {
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from AlcoholStatus where alcoholStatusType like :param", AlcoholStatus.class)
					.setParameter("param", type)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}
}
