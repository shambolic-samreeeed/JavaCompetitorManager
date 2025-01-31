package org.portfolio.competitormanager.main;

import org.portfolio.competitormanager.model.*;

public class CompetitorManagerMain {
	public static void main (String args[]) {
		Name name1 = new Name("Samreed", "Maharjan");
		Competitor competitor1 = new Competitor(2, name1, 19, "Nepal", "Advance");
		
		System.out.println(competitor1.getFullDetails());
	}
}
