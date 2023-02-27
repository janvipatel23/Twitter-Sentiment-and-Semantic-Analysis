import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import twitter4j.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// class for extracting twitter data and cleaning the raw data
public class TwitterExtraction {

    // the list of keywords whose tweets need to be extracted
    public static List<String> keywordsList = List.of("mask", "cold", "immune", "flu", "snow", "vaccine");
    // initializing the count value
    static int count = 1;
    // initializing the max count
    static int maxCount = 500;
    // the file list to store the file data
    static ArrayList<String> fileList = new ArrayList<>();

    // method for getting the twitter data
    // @param twitter - the twitter instance
    // @param database - mongodb instance
    public static void getTwitterData(Twitter twitter, MongoDatabase database) throws IOException {
        // list to store the tweets
        List<Status> tweets = new ArrayList<>();
        // iterating over the keywords list
        for(int i=0; i< keywordsList.size(); i++) {
            int tweetCount = 0;
            Query query = new Query(keywordsList.get(i));
            QueryResult queryResult;
            List<Status> queryResultTweets;
            // setting the query count to 100 for storing max 100 entry in file
            query.setCount(100);
            // fetch the tweets using the tweets search api till the tweets size is 3000 and reached to the max count
            try {
                do {
                    queryResult = twitter.search(query);
                    queryResultTweets = queryResult.getTweets();
                    tweets.addAll(queryResultTweets);
                    storeTweetsInRawFile(queryResult.getTweets(), keywordsList.get(i));
                    tweetCount = tweetCount + queryResultTweets.size();
                    System.out.println(keywordsList.get(i) + " " + queryResultTweets.size());
                } while(tweets.size() <= 3000 && (tweetCount < maxCount));
            } catch (TwitterException | IOException e) {
                e.printStackTrace();
            }
        }


        for(int i=0; i<keywordsList.size(); i++) {
            // get the mongo collection
            MongoCollection<Document> collection = database.getCollection(keywordsList.get(i));
            List<Document> entries = new ArrayList<>();
            for (int j = 0; j < fileList.size(); j++) {
                if (fileList.get(j).contains(keywordsList.get(i))) {
                    readFile(fileList.get(j), entries);
                }
            }
            // inserting the data to the collection using insertMany operation
            collection.insertMany(entries);
        }
    }

    // method for storing the raw tweets in fie
    // @param rawTweetsData - the raw tweet data
    // @param keyword - the keyword
    public static void storeTweetsInRawFile(List<Status> rawTweetsData, String keyword) throws IOException {
        // creating the file instance
        File file = new File("./Tweets/" + keyword + "_" + count +".txt");
        // creating the instance for print writer
        PrintWriter fileWriter = new PrintWriter(file);
        // adding the keyword and count to the file list
        fileList.add("./Tweets/" + keyword + "_" + count +".txt");

        // iterating over the raw tweets data
        for(Status data:rawTweetsData)
        {
            // getting the raw json data
            String raw_json_data = TwitterObjectFactory.getRawJSON(data);
            // writing to the file writer
            fileWriter.println(raw_json_data);
        }

        // closing the file writer
        fileWriter.close();
        // increasing the count
        count++;
    }

    // method for reading the file
    // @param fileName - the name of the file
    // @param entries - the list of data entries
    public static void readFile(String fileName, List<Document> entries) throws IOException {
        // creating file instance
        File file = new File(fileName);
        // the buffer reader instance
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String st;
        // reading the file reader using buffer reader
        while ((st = bufferedReader.readLine()) != null) {
            // applying the regex to clean the data
            String cleanedData = st.replaceAll("<[^>]*>", "") // removing html tags from the text.
                    .replaceAll("[^\\u0000-\\u05C0\\u2100-\\u214F]+", "") // removing an emojis
                    .replaceAll("http[s]?://[a-zA-Z0-9@./_-]+", ""); // removing urls

            // adding the clean data to the entries list
            entries.add(org.bson.Document.parse(cleanedData));
        }
    }
}
