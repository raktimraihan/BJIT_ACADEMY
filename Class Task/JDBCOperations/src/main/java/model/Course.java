package model;

public class Course {
	private Long id;
	private String name;
	private Integer totalHour;
	private String enrollStatus;
	
	public Course(String name, Integer totalHour, String enrollStatus) {
		super();
		this.name = name;
		this.totalHour = totalHour;
		this.enrollStatus = enrollStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(Integer totalHour) {
		this.totalHour = totalHour;
	}

	public String getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(String enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", totalHour=" + totalHour + ", enrollStatus=" + enrollStatus
				+ "]";
	}
	
}
