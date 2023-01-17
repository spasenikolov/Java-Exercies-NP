package secondexam.footballtable;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class FootballTable {
    Map<String, Team> teamsByName;


    public FootballTable() {
        this.teamsByName = new HashMap<>();
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        teamsByName.putIfAbsent(homeTeam, new Team(homeTeam));
        teamsByName.putIfAbsent(awayTeam, new Team(awayTeam));

        teamsByName.get(homeTeam).setParameters(homeGoals,awayGoals);
        teamsByName.get(awayTeam).setParameters(awayGoals, homeGoals);

    }



    public void printTable() {
        List<Team> teams = new ArrayList<>(teamsByName.values());
        teams.sort(Comparator
                .comparing(Team::getTotalPoints,Comparator.reverseOrder())
                .thenComparing(Team::getGoalLambda)
                .thenComparing(Comparator.comparing(Team::getTeamName)));

        int i=1;
        for (Team team : teams) {
            System.out.printf("%2d. ",i++);
            System.out.println(team);
        }
    }
}
