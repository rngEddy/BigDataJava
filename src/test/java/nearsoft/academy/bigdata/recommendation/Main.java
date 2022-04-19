package nearsoft.academy.bigdata.recommendation;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String args[]){
        System.out.println("hello world");
        MovieRecommender mr = new MovieRecommender("movies.txt");
        int totalUser = mr.getTotalUsers();
        int totalReview, totalProducts;
        List<String> prueba = new LinkedList<String>();        
        System.out.println(totalUser);
        System.out.println(totalReview=mr.getTotalReviews());
        System.out.println(totalProducts=mr.getTotalProducts());
        System.out.println(prueba=mr.getRecommendationsForUser("A1I7QGUDP043DG"));
    }
}
