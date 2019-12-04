package by.eslaikouskaya;

import by.eslaikouskaya.pojo.*;
import by.eslaikouskaya.repository.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class HomeController {

	private static Logger log = Logger.getLogger("HomeController");

	private int habits;

	private StringBuilder recommendation;

	@Autowired
	MyUserService myUserService;

	@Autowired
	SexRepo sexRepo;

	@Autowired
	AlcoholStatusRepo alcoholStatusRepo;

	@Autowired
	BiteStatusRepo biteStatusRepo;

	@Autowired
	BreakfastStatusRepo breakfastStatusRepo;

	@Autowired
	MyResultRepo myResultRepo;

	@Autowired
	SmokingStatusRepo smokingStatusRepo;

	@Autowired
	PhysicalExercisesRepo physicalExercisesRepo;

	@GetMapping
	public ModelAndView showLoginView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("home");
		return view;
	}

	@PostMapping("/home")
	public String submitForm(@RequestParam int age, @RequestParam String sex,
	                         @RequestParam int weight, @RequestParam int height,
	                         @RequestParam int psg, @RequestParam int sleepingHours,
	                         @RequestParam String physicalExercisesStatus,
	                         @RequestParam String smokingStatus,
	                         @RequestParam String alcoholStatus,
	                         @RequestParam String breakfastStatus,
	                         @RequestParam String biteStatus,
	                         Model model){

		MyUser myUser = new MyUser();
		habits=0;

		if (sex.equals("female")){
			Sex sexFromRepo = sexRepo.findByType(SexType.FEMALE);
			log.info("what we found in repo: "+sexFromRepo);
			if (sexFromRepo==null){
				sexFromRepo = new Sex();
				sexFromRepo.setSexType(SexType.FEMALE);
				sexRepo.add(sexFromRepo);
			}
			myUser.setSex(sexFromRepo);
		} else {
			Sex sexFromRepo = sexRepo.findByType(SexType.MALE);
			if (sexFromRepo == null){
				sexFromRepo = new Sex();
				sexFromRepo.setSexType(SexType.MALE);
				sexRepo.add(sexFromRepo);
			}
			myUser.setSex(sexFromRepo);
		}


		myUser.setAge(age);
		myUser.setWeight(weight);
		myUser.setHeight(height);
		myUser.setPsg(psg);
		recommendation = new StringBuilder();
		if (psg>30) {
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Снизьте процент жира.");
		}
		else habits++;
		myUser.setSleepingHours(sleepingHours);
		if (sleepingHours==7 | sleepingHours==8) habits++;
		else {
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Спите 7-8 часов.");
		}
		setPhysicalExercisesStatus(physicalExercisesStatus, myUser);
		setSmokingStatus(smokingStatus, myUser);
		setAlcoholStatus(alcoholStatus, myUser);
		setBreakfastStatus(breakfastStatus, myUser);
		setBiteStatus(biteStatus, myUser);
		setResult(age, myUser, model);
		myUserService.add(myUser);
		return "result";
	}

	private void setResult(int age, MyUser myUser, Model model) {
		MyResult myResult = new MyResult();
		int passportAge=0;
		if (age<=25) passportAge=20;
		if (age>25 & age<=35) passportAge=30;
		if (age>35 & age<=45) passportAge=40;
		if (age>45 & age<=55) passportAge=50;
		if (age>55 & age<=65) passportAge=60;
		if (age>65) passportAge=70;
		switch (habits) {
			case 3: {switch (passportAge){
				case 20: myResult.setBioAge(age+7.4); break;
				case 30: myResult.setBioAge(age+9.1); break;
				case 40: myResult.setBioAge(age+10.7); break;
				case 50: myResult.setBioAge(age+12.4); break;
				case 60: myResult.setBioAge(age+14.0); break;
				case 70: myResult.setBioAge(age+15.7); break;
			} break;
			}
			case 4: {switch (passportAge){
				case 20: myResult.setBioAge(age+0.5); break;
				case 30: myResult.setBioAge(age+3.0); break;
				case 40: myResult.setBioAge(age+5.4); break;
				case 50: myResult.setBioAge(age+7.9); break;
				case 60: myResult.setBioAge(age+10.4); break;
				case 70: myResult.setBioAge(age+12.8); break;
			} break;
			}
			case 5: {switch (passportAge){
				case 20: myResult.setBioAge(age-1.1); break;
				case 30: myResult.setBioAge(age-0.6); break;
				case 40: myResult.setBioAge(age-0.1); break;
				case 50: myResult.setBioAge(age+0.3); break;
				case 60: myResult.setBioAge(age+0.8); break;
				case 70: myResult.setBioAge(age+1.3); break;
			} break;
			}
			case 6: {switch (passportAge){
				case 20: myResult.setBioAge(age-4.2); break;
				case 30: myResult.setBioAge(age-4.7); break;
				case 40: myResult.setBioAge(age-5.2); break;
				case 50: myResult.setBioAge(age-5.7); break;
				case 60: myResult.setBioAge(age-6.2); break;
				case 70: myResult.setBioAge(age-6.8); break;
			} break;
			}
			case 7: {switch (passportAge){
				case 20: myResult.setBioAge(age-9.4); break;
				case 30: myResult.setBioAge(age-11.1); break;
				case 40: myResult.setBioAge(age-12.9); break;
				case 50: myResult.setBioAge(age-14.7); break;
				case 60: myResult.setBioAge(age-16.4); break;
				case 70: myResult.setBioAge(age-18.2); break;
			} break;
			}
			default: {switch (passportAge){
				case 20: myResult.setBioAge(age+14.3); break;
				case 30: myResult.setBioAge(age+16.9); break;
				case 40: myResult.setBioAge(age+19.4); break;
				case 50: myResult.setBioAge(age+22.0); break;
				case 60: myResult.setBioAge(age+24.5); break;
				case 70: myResult.setBioAge(age+27.1); break;
			} break;
			}
		}
		if (habits==7) {
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Вы ведете здоровый образ жизни. Так держать!");
		}
		myResult.setHabits(habits);
		myResult.setRecommendation(recommendation.toString());
		myResultRepo.add(myResult);
		myUser.setMyResult(myResult);
		model.addAttribute("result", myResult);
	}

	private void setBiteStatus(String biteStatusString, MyUser myUser) {
		if (biteStatusString.equals("always")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Не перекусывайте.");
			BiteStatus biteStatus = biteStatusRepo.findByType(BiteStatusType.ALWAYS);
			if (biteStatus==null) {
				biteStatus = new BiteStatus();
				biteStatus.setBiteStatusType(BiteStatusType.ALWAYS);
				biteStatusRepo.add(biteStatus);
			}
			myUser.setBiteStatus(biteStatus);

		}
		if (biteStatusString.equals("sometimes")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Не перекусывайте.");
			BiteStatus biteStatus = biteStatusRepo.findByType(BiteStatusType.SOMETIMES);
			if (biteStatus==null) {
				biteStatus = new BiteStatus();
				biteStatus.setBiteStatusType(BiteStatusType.SOMETIMES);
				biteStatusRepo.add(biteStatus);
			}
			myUser.setBiteStatus(biteStatus);

		}
		if (biteStatusString.equals("rarely")){
			habits++;
			 BiteStatus biteStatus = biteStatusRepo.findByType(BiteStatusType.RARELY_NEVER);
			if (biteStatus==null) {
				biteStatus = new BiteStatus();
				biteStatus.setBiteStatusType(BiteStatusType.RARELY_NEVER);
				biteStatusRepo.add(biteStatus);
			}
			if (biteStatusRepo.findByType(BiteStatusType.RARELY_NEVER)==null)
				biteStatusRepo.add(new BiteStatus(BiteStatusType.RARELY_NEVER));
			myUser.setBiteStatus(biteStatus);

		}
	}

	private void setBreakfastStatus(String breakfastStatusString, MyUser myUser) {
		if (breakfastStatusString.equals("everyday")){
			habits++;
			BreakfastStatus breakfastStatus = breakfastStatusRepo.findByType(BreakfastStatusType.EVERYDAY);
			if (breakfastStatus==null) {
				breakfastStatus = new BreakfastStatus();
				breakfastStatus.setBreakfastStatusType(BreakfastStatusType.EVERYDAY);
				breakfastStatusRepo.add(breakfastStatus);
			}
			myUser.setBreakfastStatus(breakfastStatus);

		}
		if (breakfastStatusString.equals("sometimes")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Регулярно завтракайте.");
			BreakfastStatus breakfastStatus = breakfastStatusRepo.findByType(BreakfastStatusType.SOMETIMES);
			if (breakfastStatus==null) {
				breakfastStatus = new BreakfastStatus();
				breakfastStatus.setBreakfastStatusType(BreakfastStatusType.SOMETIMES);
				breakfastStatusRepo.add(breakfastStatus);
			}
			myUser.setBreakfastStatus(breakfastStatus);

		}
		if (breakfastStatusString.equals("rarely")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Регулярно завтракайте.");
			BreakfastStatus breakfastStatus = breakfastStatusRepo.findByType(BreakfastStatusType.RARELY_NEVER);
			if (breakfastStatus==null) {
				breakfastStatus = new BreakfastStatus();
				breakfastStatus.setBreakfastStatusType(BreakfastStatusType.RARELY_NEVER);
				breakfastStatusRepo.add(breakfastStatus);
			}
			myUser.setBreakfastStatus(breakfastStatus);

		}
	}

	private void setAlcoholStatus(String alcoholStatusString, MyUser myUser) {
		if (alcoholStatusString.equals("often")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Не употребляйте алкоголь.");
			AlcoholStatus alcoholStatus = alcoholStatusRepo.findByType(AlcoholStatusType.OFTEN);
			if (alcoholStatus==null){
				alcoholStatus = new AlcoholStatus();
				alcoholStatus.setAlcoholStatusType(AlcoholStatusType.OFTEN);
				alcoholStatusRepo.add(alcoholStatus);
			}
			myUser.setAlcoholStatus(alcoholStatus);

		}
		if (alcoholStatusString.equals("rarely")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Не употребляйте алкоголь.");
			AlcoholStatus alcoholStatus = alcoholStatusRepo.findByType(AlcoholStatusType.RARELY);
			if (alcoholStatus==null){
				alcoholStatus = new AlcoholStatus();
				alcoholStatus.setAlcoholStatusType(AlcoholStatusType.RARELY);
				alcoholStatusRepo.add(alcoholStatus);
			}
			myUser.setAlcoholStatus(alcoholStatus);

		}
		if (alcoholStatusString.equals("do_not")){
			habits++;
			AlcoholStatus alcoholStatus = alcoholStatusRepo.findByType(AlcoholStatusType.DO_NOT);
			if (alcoholStatus==null){
				alcoholStatus = new AlcoholStatus();
				alcoholStatus.setAlcoholStatusType(AlcoholStatusType.DO_NOT);
				alcoholStatusRepo.add(alcoholStatus);
			}
			myUser.setAlcoholStatus(alcoholStatus);

		}
	}

	private void setSmokingStatus(String smokingStatusString, MyUser myUser) {
		if (smokingStatusString.equals("never")) {
			habits++;
			SmokingStatus smokingStatus = smokingStatusRepo.findByType(SmokingStatusType.NEVER);
			if (smokingStatus==null){
				smokingStatus= new SmokingStatus();
				smokingStatus.setSmokingStatusType(SmokingStatusType.NEVER);
				smokingStatusRepo.add(smokingStatus);
			}
			myUser.setSmokingStatus(smokingStatus);

		}
		if (smokingStatusString.equals("give_up")){
			habits++;
			SmokingStatus smokingStatus = smokingStatusRepo.findByType(SmokingStatusType.GIVE_UP);
			if (smokingStatus==null){
				smokingStatus= new SmokingStatus();
				smokingStatus.setSmokingStatusType(SmokingStatusType.GIVE_UP);
				smokingStatusRepo.add(smokingStatus);
			}
			myUser.setSmokingStatus(smokingStatus);

		}
		if (smokingStatusString.equals("less_than_box")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Бросьте курить.");
			SmokingStatus smokingStatus = smokingStatusRepo.findByType(SmokingStatusType.LESS_THAN_BOX);
			if (smokingStatus==null){
				smokingStatus= new SmokingStatus();
				smokingStatus.setSmokingStatusType(SmokingStatusType.LESS_THAN_BOX);
				smokingStatusRepo.add(smokingStatus);
			}
			myUser.setSmokingStatus(smokingStatus);

		}
		if (smokingStatusString.equals("more_than_box")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Бросьте курить.");
			SmokingStatus smokingStatus = smokingStatusRepo.findByType(SmokingStatusType.MORE_THAN_BOX);
			if (smokingStatus==null){
				smokingStatus= new SmokingStatus();
				smokingStatus.setSmokingStatusType(SmokingStatusType.MORE_THAN_BOX);
				smokingStatusRepo.add(smokingStatus);
			}
			myUser.setSmokingStatus(smokingStatus);
		}
	}

	private void setPhysicalExercisesStatus(String physicalExercisesStatusString, MyUser myUser) {
		if (physicalExercisesStatusString.equals("everyday")){
			habits++;
			PhysicalExercisesStatus physicalExercisesStatus = physicalExercisesRepo.findByType(PhysicalExercisesStatusType.EVERYDAY);
			if (physicalExercisesStatus==null){
				physicalExercisesStatus = new PhysicalExercisesStatus();
				physicalExercisesStatus.setPhysicalExercisesStatusType(PhysicalExercisesStatusType.EVERYDAY);
				physicalExercisesRepo.add(physicalExercisesStatus);
			}
			myUser.setPhysicalExercisesStatus(physicalExercisesStatus);
		}

		if (physicalExercisesStatusString.equals("few_times_per_week")){
			habits++;
			PhysicalExercisesStatus physicalExercisesStatus = physicalExercisesRepo.findByType(PhysicalExercisesStatusType.FEW_TIMES_PER_WEEK);
			if (physicalExercisesStatus==null){
				physicalExercisesStatus = new PhysicalExercisesStatus();
				physicalExercisesStatus.setPhysicalExercisesStatusType(PhysicalExercisesStatusType.FEW_TIMES_PER_WEEK);
				physicalExercisesRepo.add(physicalExercisesStatus);
			}
			myUser.setPhysicalExercisesStatus(physicalExercisesStatus);
		}

		if (physicalExercisesStatusString.equals("rarely")){
			if (recommendation.length()>1) recommendation.append("\n");
			recommendation.append("Занимайтесь физическими упражнениями.");
			PhysicalExercisesStatus physicalExercisesStatus = physicalExercisesRepo.findByType(PhysicalExercisesStatusType.RARELY);
			if (physicalExercisesStatus==null){
				physicalExercisesStatus = new PhysicalExercisesStatus();
				physicalExercisesStatus.setPhysicalExercisesStatusType(PhysicalExercisesStatusType.RARELY);
				physicalExercisesRepo.add(physicalExercisesStatus);
			}
			myUser.setPhysicalExercisesStatus(physicalExercisesStatus);
		}
	}



}
