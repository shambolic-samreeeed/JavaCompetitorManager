package org.portfolio.competitormanager.main;

import org.portfolio.competitormanager.model.*;
import java.sql.SQLException;
import org.portfolio.competitormanager.dao.*;
import org.portfolio.competitormanager.dao.impl.*;

public class CompetitorManagerMain {
	public static void main (String args[]) throws ClassNotFoundException, SQLException {
		
		CompetitorDao competitorDao = new CompetitorDaoImpl();
		
		Name name1 = new Name("Samreed", "Maharjan");
		Competitor competitor1 = new Competitor(2, name1, 19, "Nepal", new int[] {1,2,3,4,5});
		
		Name name2 = new Name("Messi", "Lionel");
		Competitor competitor2 = new Competitor(3, name2, 38, "Argentina", new int[] {2,5,4,6,8,7,4,5});
		
		
		competitorDao.save(competitor1);
		competitorDao.save(competitor2);
		
		System.out.println(competitor1.getFullDetails());
	}
}
