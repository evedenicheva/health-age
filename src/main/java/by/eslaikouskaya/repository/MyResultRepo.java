package by.eslaikouskaya.repository;

import by.eslaikouskaya.pojo.MyResult;
import by.eslaikouskaya.pojo.Sex;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MyResultRepo {

	private static Logger log = Logger.getLogger("MyResultRepo");

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(MyResult myResult) {
		sessionFactory.getCurrentSession().save(myResult);
	}


}
