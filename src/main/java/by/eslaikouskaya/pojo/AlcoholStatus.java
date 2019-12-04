package by.eslaikouskaya.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class AlcoholStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private AlcoholStatusType alcoholStatusType;
	@OneToMany(mappedBy = "alcoholStatus")
	private List <MyUser>  myUsers;

	public AlcoholStatus(AlcoholStatusType alcoholStatusType){
		this.alcoholStatusType = alcoholStatusType;
	}
}
