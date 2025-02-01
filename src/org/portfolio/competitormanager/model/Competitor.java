/**
 * The Competitor class represents a competitor with an ID, name, age, country,
 * and an array of scores. It provides methods to calculate the overall score,
 * determine the competitor's level (Beginner, Intermediate, Advanced), and
 * retrieve full details.
 */

package org.portfolio.competitormanager.model;

import java.util.Arrays;

public class Competitor {
	private int competitor_id;
	private Name name;
	private int age;
	private String country;
	private int[] scores; 
	
	// Constructor to initialize a Competitor object.
	public Competitor(int competitor_id, Name name, int age, String country, int[] scores) {
		this.competitor_id = competitor_id;
		this.name= name;
		this.age = age;
		this.country = country;
		this.scores = scores;
	}
	
	//getter and setter methods 
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
	
	public int[] getScores() {
		return scores;
	}
	

	// calculating the overall scores of the competitors.
	public double getOverallScore() {
	    if (scores.length == 0) return 0;
	    

	    if (scores.length <= 2) {
	        return Double.parseDouble(String.format("%.2f", Arrays.stream(scores).sum() / (double) scores.length));
	    }

	    Arrays.sort(scores);
	    
	    int sum = 0;
	    
	    for (int i = 1; i < scores.length - 1; i++) {
	        sum += scores[i];
	    }
	    
	    return Double.parseDouble(String.format("%.2f", sum / (double) (scores.length - 2)));
	}

	//categorizing the competitor levels according to their overall scores.
	public String getLevel() {
	    double overallScore = getOverallScore();
	    
	    // Assuming the maximum score is 10
	    double maxScore = 10.0;

	    double percentage = (overallScore / maxScore) * 100;
	    

	    if (percentage <= 30) {
	        return "Beginner";
	    } else if (percentage <= 70) {
	        return "Intermediate";
	    } else {
	        return "Advanced";
	    }
	}



	public String getFullDetails() {
		return "Competitor number " + competitor_id + ", Name " + name + ", country " + country + ". " + name + "aged " + age + " and has an overall score of " + getOverallScore();
	}
	

}
