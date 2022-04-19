package nearsoft.academy.bigdata.recommendation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MovieRecommender {
    
    String PATH;
    LinkedList<Product> products = new LinkedList<Product>();
    LinkedList<User> users = new LinkedList<User>();
    LinkedList<Review> reviews = new LinkedList<Review>();

    public MovieRecommender(String PATH) {
        this.PATH = PATH;

        readFile();
    }

    public int getTotalReviews(){
        return reviews.size();
    }

    public int getTotalProducts(){
        return products.size();
    }

    public int getTotalUsers(){
        return users.size();
    }

    public List<String> getRecommendationsForUser(String recommendation){
        List<String> RecommendationForUser = new LinkedList<String>();
        for(Review r : reviews){
            if(r.user.userID.equals(recommendation)){
                RecommendationForUser.add(r.product.productID);
            }
        }
        return RecommendationForUser;
    }

    private void readFile(){
        // File path is passed as parameter
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(PATH).getFile());
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
        try{ 
                BufferedReader br
                = new BufferedReader(new FileReader(file));
            
    
            // Declaring a string variable
            String st;
            // Condition holds true till
            // there is character in a string
            Product product = new Product();
            User user = new User();
            Review review = new Review();
            while ((st = br.readLine()) != null){
                if(st.startsWith("product/productId")){
                    String[] productIDParts = st.split(":");
                    product.productID = productIDParts[1].trim();
                }

                if(st.startsWith("review/userId")){
                    String[] userIDParts = st.split(":");
                    user.userID = userIDParts[1].trim();
                }

                if(st.startsWith("review/profileName")){
                    String[] userProfileNameParts = st.split(":");
                    user.profileName = userProfileNameParts[1].trim();
                }

                if(st.startsWith("review/helpfulness")){
                    String[] reviewHelpfulnessParts = st.split(":");
                    review.helpfulness = reviewHelpfulnessParts[1].trim();
                }

                if(st.startsWith("review/score")){
                    String[] reviewScoreParts = st.split(":");
                    review.score = Float.parseFloat(reviewScoreParts[1].trim());
                }

                if(st.startsWith("review/time")){
                    String[] reviewTimeParts = st.split(":");
                    review.time = reviewTimeParts[1].trim();
                }

                if(st.startsWith("review/summary")){
                    String[] reviewSummaryParts = st.split(":");
                    review.summary = reviewSummaryParts[1].trim();
                }

                if(st.startsWith("review/text")){
                    String[] reviewTextParts = st.split(":");
                    review.text = reviewTextParts[1].trim();
               
                    verifyProduct(product);
                    verifyUser(user);
                    review.product = product;
                    review.user = user;
                    reviews.add(review);
                    
                    product = new Product();
                    user = new User();
                    review = new Review();

                    System.out.println("agrego usuario " +reviews.size());
                }
            }
    
                // Print the string
                //System.out.println(st);
        } catch(IOException e) {
            System.out.println(e);
        }    
    }

    void verifyProduct(Product product){
        boolean agregar = true;
        for(Product p : products){
            if(p.productID.equals(product.productID)){
                agregar=false;
                break;
            }    
        }
        if(agregar)
            products.add(product);
    }

    void verifyUser(User user){
        boolean agregar = true;
        for(User u : users){
            if(u.userID.equals(user.userID)){
                agregar=false;
                break;
            }    
        }
        if(agregar)
            users.add(user);
    }



}
