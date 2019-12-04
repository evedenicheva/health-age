package by.eslaikouskaya.repository;

import by.eslaikouskaya.pojo.MyUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Repository
public class MyUserRepo {

	private static Logger log = Logger.getLogger("MyUserRepo");

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void add(MyUser myUser) {
		sessionFactory.getCurrentSession().save(myUser);
	}
}
