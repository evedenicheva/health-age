package by.eslaikouskaya.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class BreakfastStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BreakfastStatusType breakfastStatusType;
	@OneToMany(mappedBy = "breakfastStatus")
	private List <MyUser> myUsers;

	public BreakfastStatus(BreakfastStatusType breakfastStatusType){
		this.breakfastStatusType = breakfastStatusType;
	}
}
