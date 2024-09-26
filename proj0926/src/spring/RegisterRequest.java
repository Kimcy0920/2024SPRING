package spring;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterRequest {
	
	private Long id;
	private String name;
	private String city;
	private String street;
	private String zipcode;
	
	public RegisterRequest(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
