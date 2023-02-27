import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.Arrays;

// the class for the word count
public class WordCount {
    public static void main(String[] args) {
        // setting the master node for the spark configuration
        SparkConf conf = new SparkConf().setMaster("spark://data-assignment-4-csci-5408-w21.us-central1-a.c.csci-5408-w21-340805.internal:7077").setAppName("WordCount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Load our input data.
        String inputFile = "file:///home/patel_janvi23798/Tweets";

        // Passing the whole file folder as the input file
        JavaPairRDD<String, String> input = sc.wholeTextFiles(inputFile);

        // Apply the flat function and convert the string into lower case and then split it using regex
        // Apply the filter on the string for "snow", "flu", and "cold" words
        // Apply the map function to the string and return the required keyword only from the whole substring
        // In order to return total count of words which acts as independently and also which is part of substrings
        JavaRDD<String> words = input.flatMap(l -> Arrays.asList(l._2().toLowerCase().split("[{}\",: .-_]")).iterator())
                .filter(l -> l.contains("snow") || l.contains("flu") || l.contains("cold"))
                .map(m -> {
                    if(m.contains("snow")) {
                        return "snow";
                    } else if (m.contains("flu")) {
                        return "flu";
                    } else if (m.contains("cold")) {
                        return "cold";
                    }
                    return null;
                });

        // Transform into pairs and count.
        JavaPairRDD< String, Integer > pairs = words.mapToPair(w -> new Tuple2(w, 1));

        // Sum the same keys count using reduceByKey function
        JavaPairRDD < String, Integer > counts = pairs.reduceByKey((x, y) -> x + y);

        // Printing out the total counts for each required word using collect function
        System.out.println(counts.collect());
    }
}