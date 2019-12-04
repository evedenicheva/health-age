package by.eslaikouskaya.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sex {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private SexType sexType;
	@OneToMany(mappedBy = "sex")
	private List<MyUser> myUsers;

	public Sex(SexType sexType) {
		this.sexType = sexType;
	}

	@Override
	public String toString() {
		return "Sex{" +
				"id=" + id +
				", sexType=" + sexType +'}';
	}
}
