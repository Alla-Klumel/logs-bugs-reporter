package telran.logs.bugs.dto;

import java.util.Objects;

import javax.validation.constraints.*;


public class ProgrammerDto {
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + (int) (id ^ (id >>> 32));
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ProgrammerDto other = (ProgrammerDto) obj;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		} else if (!email.equals(other.email))
//			return false;
//		if (id != other.id)
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
	@Override
	public int hashCode() {
		return Objects.hash(email, id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgrammerDto other = (ProgrammerDto) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name);
	}
	
	public ProgrammerDto(@Min(1) long id, @NotEmpty String name, @Email String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	@Min(1)
	public long id;
	@NotEmpty
	public String name;
	@Email
	public String email;
	

}
