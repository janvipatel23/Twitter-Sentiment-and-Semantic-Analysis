import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

// class for twitter configuration
public class TwitterConfiguration {

    // method for getting twitter instance
    // @return twitter - returning the twitter instance
    public static Twitter getTwitterInstance() {
        // getting the configuration builder
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                // setting the credentials
                .setOAuthConsumerKey("AuR0OQbPaQJu5r3WvYruNOR83")
                .setOAuthConsumerSecret("crPwQyPioyJIlJC0nsOQe2iZeRkoUY8oEeU0Eu1QGZ4L4WpKWK")
                .setOAuthAccessToken("1499839353635418113-BNYwYHtWC9F5VHXEm4E7etpkzWpFJp")
                .setOAuthAccessTokenSecret("oFShd460hcpRzj2wLIDK6IJOERrgSxogeT8mTGTfhTMRB")
                .setJSONStoreEnabled(true);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        return twitter;
    }
}
