package nearsoft.academy.bigdata.recommendation;

public class Review {
    
    Product product;
    User user;
    String helpfulness, summary, time, text;
    float score; 

    public Review(Product product, User user, String helpfulness, String summary, String time, String text, float score) {
        this.product= product;
        this.user = user;
        this.helpfulness = helpfulness;
        this.summary = summary;
        this.time = time;
        this.text = text;
        this.score = score;
    }

    public Review(){
        
    }
}
