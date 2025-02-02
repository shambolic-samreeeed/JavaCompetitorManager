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
		
		
		Name name3 = new Name("Pablo", "Gavi");
		Competitor updatedCompetitor = new Competitor(5, name3, 38, "Canada", new int[] {3});
		
		try {
			int result = competitorDao.update(updatedCompetitor, 4);
			if(result>0) {
				System.out.println("Competitor updated sucessfully.");
			}else {
				System.out.println("Failed");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			int deleteResult = competitorDao.remove(4);
			if(deleteResult>0) {
				System.out.println("Competitor deleted sucessfully.");
			}else {
				System.out.println("Deletion Failed.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(competitor1.getFullDetails());
	}
}
