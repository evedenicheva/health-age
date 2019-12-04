package by.eslaikouskaya.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class PhysicalExercisesStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private PhysicalExercisesStatusType physicalExercisesStatusType;
	@OneToMany(mappedBy = "physicalExercisesStatus")
	private List <MyUser> myUsers;

	public PhysicalExercisesStatus(PhysicalExercisesStatusType physicalExercisesStatusType){
		this.physicalExercisesStatusType = physicalExercisesStatusType;
	}

	@Override
	public String toString() {
		return "PhysicalExercisesStatus{" +
				"id=" + id +
				", physicalExercisesStatusType=" + physicalExercisesStatusType +
				'}';
	}
}
