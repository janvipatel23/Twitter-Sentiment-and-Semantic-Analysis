import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import twitter4j.Twitter;
import java.io.IOException;

// the main class for twitter data search
public class TwitterDataSearch {
    public static void main(String[] args) throws IOException {

        // building connection string for the mongo db
        ConnectionString connection = new ConnectionString("mongodb+srv://janvi:Zt2cCgL8FNIcAo4y@cluster0.t0d8l.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connection).build();
        // creating the client
        MongoClient client = MongoClients.create(clientSettings);
        // getting the database
        MongoDatabase database = client.getDatabase("TweetsData");

        // getting the twitter instance
        Twitter twitterInstance = TwitterConfiguration.getTwitterInstance();
        // invoking the getTwitterData method with twitterInstance and database
        TwitterExtraction.getTwitterData(twitterInstance, database);
    }
}
