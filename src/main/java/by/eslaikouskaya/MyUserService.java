package by.eslaikouskaya;

import by.eslaikouskaya.pojo.MyUser;
import by.eslaikouskaya.repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MyUserService {

	@Autowired
	MyUserRepo myUserRepo;

	@Transactional
	public void add(MyUser myUser){
		myUserRepo.add(myUser);
	}
}
