package by.eslaikouskaya.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MyUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int age;
	@ManyToOne
	private Sex sex;
	private int weight;
	private int height;
	private int psg;
	private int sleepingHours;
	@ManyToOne
	private PhysicalExercisesStatus physicalExercisesStatus;
	@ManyToOne
	private SmokingStatus smokingStatus;
	@ManyToOne
	private AlcoholStatus alcoholStatus;
	@ManyToOne
	private BreakfastStatus breakfastStatus;
	@ManyToOne
	private BiteStatus biteStatus;
	@ManyToOne
	private MyResult myResult;

	@Override
	public String toString() {
		return "MyUser{" +
				"id=" + id +
				", age=" + age +
				", sex=" + sex +
				", weight=" + weight +
				", height=" + height +
				", psg=" + psg +
				", sleepingHours=" + sleepingHours +
				", physicalExercisesStatus=" + physicalExercisesStatus +
				", smokingStatus=" + smokingStatus +
				", alcoholStatus=" + alcoholStatus +
				", breakfastStatus=" + breakfastStatus +
				", biteStatus=" + biteStatus +
				", myResult=" + myResult +
				'}';
	}
}
