package by.eslaikouskaya.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SmokingStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private SmokingStatusType smokingStatusType;
	@OneToMany(mappedBy = "smokingStatus")
	private List <MyUser> myUsers;

	public SmokingStatus(SmokingStatusType smokingStatusType){
		this.smokingStatusType = smokingStatusType;
	}
}
