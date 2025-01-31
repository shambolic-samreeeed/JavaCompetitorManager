package org.portfolio.competitormanager.model;

public class Competitor {
	private int competitor_id;
	private Name name;
	private int age;
	private String country;
	private String level;
	
	public Competitor(int competitor_id, Name name, int age, String country, String level) {
		this.competitor_id = competitor_id;
		this.name= name;
		this.age = age;
		this.country = country;
		this.level = level;
	}
	
	public int getCompetitorId() {
		return competitor_id;
	}
	
	public void setCompetitorId(int competitor_id) {
		this.competitor_id= competitor_id;
	}
	
	public Name getName() {
		return name;
	}
	
	public void setName(Name name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public int getOverallScore() {
		return 5;
	}
	
	public String getFullDetails() {
		return "Competitor number " + competitor_id + ", Name " + name + ", country " + country + ". " + name + "is a " + level + " aged " + age + " and has an overall score of " + getOverallScore();
	}
}
