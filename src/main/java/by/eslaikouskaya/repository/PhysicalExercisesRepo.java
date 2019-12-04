package by.eslaikouskaya.repository;

import by.eslaikouskaya.pojo.BreakfastStatus;
import by.eslaikouskaya.pojo.BreakfastStatusType;
import by.eslaikouskaya.pojo.PhysicalExercisesStatus;
import by.eslaikouskaya.pojo.PhysicalExercisesStatusType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PhysicalExercisesRepo {

	private static Logger log = Logger.getLogger("PhysicalExercisesStatusRepo");

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(PhysicalExercisesStatus physicalExercisesStatus) {
		sessionFactory.getCurrentSession().save(physicalExercisesStatus);
	}

	@Transactional
	public PhysicalExercisesStatus findByType(PhysicalExercisesStatusType type) {
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from PhysicalExercisesStatus where physicalExercisesStatusType like :param", PhysicalExercisesStatus.class)
					.setParameter("param", type)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}
}
