module CompetitorManager {
	requires java.desktop;
	requires java.sql;
	requires junit.platform.console.standalone;

	exports org.portfolio.competitormanager.main;
	exports org.portfolio.competitormanager.test;

	opens org.portfolio.competitormanager.test to junit.platform.console.standalone, org.junit.jupiter.api;
}
