package by.eslaikouskaya.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class BiteStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BiteStatusType biteStatusType;
	@OneToMany(mappedBy = "biteStatus")
	private List<MyUser> myUsers;

	public BiteStatus(BiteStatusType biteStatusType){
		this.biteStatusType = biteStatusType;
	}
}
