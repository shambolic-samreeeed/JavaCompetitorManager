package org.portfolio.competitormanager.model;

import java.util.Arrays;

public class Competitor {
	private int competitor_id;
	private Name name;
	private int age;
	private String country;
	private int[] scores; 
	
	public Competitor(int competitor_id, Name name, int age, String country, int[] scores) {
		this.competitor_id = competitor_id;
		this.name= name;
		this.age = age;
		this.country = country;
		this.scores = scores;
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
		double overallScore = getOverallScore();
		
		if(overallScore <=2.5) {
			return "Beginner";
		}else if (overallScore <=4.0) {
			return "Intermediate";
		}else {
			return "Advance";
		}
	}

	
	public int[] getScores() {
		return scores;
	}
	public double getOverallScore() {
		if(scores.length ==0) return 0;
		
		Arrays.sort(scores);
		int sum=0;
		for(int i=1; i<scores.length-1;i++) {
			sum+=scores[i];
		}
		return scores.length>2?(double)sum/(scores.length -2) : (double) sum / scores.length;
	}
	
	public String getFullDetails() {
		return "Competitor number " + competitor_id + ", Name " + name + ", country " + country + ". " + name + "aged " + age + " and has an overall score of " + getOverallScore();
	}
	

}
