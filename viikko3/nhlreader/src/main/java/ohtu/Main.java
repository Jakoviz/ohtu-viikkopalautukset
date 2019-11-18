package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        System.out.println("Players from FIN\n");
        Arrays.sort(players, new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return (o2.getAssists() + o2.getGoals()) - (o1.getAssists() + o1.getGoals());
            }
        });
        for (Player player : players) {
	        if (player.getNationality().equals("FIN")) {
                System.out.printf("%22s %3s %2d + %2d = %2d%n", player, player.getTeam(), player.getGoals(), player.getAssists(), player.getGoals() + player.getAssists());
            }	
        }   
    }
  
}