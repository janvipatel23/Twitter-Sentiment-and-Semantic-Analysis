# Assignment-4
## Twitter Extraction and Twitter Cleaning
#### Task: To extract the tweets for the specific word, store the raw data in the file, clean the data, and then store it in mongo database

Steps to run the project:
1. Clone the project
2. If you want to use your mongodb then change the connection string in TwitterDataSearch class.
3. Run the project using any code IDE.

About the dependency:
Added the dependency for twitter4j and mongodb in the pom.xml file

About the project: Following classes are included in the project

1. TwitterConfiguration - The public class for getting the twitter configuration.
2. TwitterExtraction - The public class for extracting the tweets' data, storing into the file, cleaning the data, and store into mongodb
3. TwitterDataSearch - The main class for getting db connection, database and the twitter instance.

##References:
[1] “Search tweets introduction twitter developer platform,” Twitter. [Online]. Available: https://developer.twitter.com/en/docs/twitter-api/tweets/search/introduction. [Accessed: 05-Mar-2022].

[2] “Overview twitter developer platform,” Twitter. [Online]. Available: https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/overview. [Accessed: 05-Mar-2022].

[3] Twitter. [Online]. Available: https://developer.twitter.com/en/portal/products/elevated. [Accessed: 06-Mar-2022].

[4] “Introduction to twitter4j,” Baeldung, 12-Feb-2020. [Online]. Available: https://www.baeldung.com/twitter4j. [Accessed: 06-Mar-2022].

[5] “Get more than 100 most trending tweets using Twitter4j,” Stack Overflow, 01-Nov-1963. [Online]. Available: https://stackoverflow.com/questions/34560305/get-more-than-100-most-trending-tweets-using-twitter4j. [Accessed: 07-Mar-2022].

[6] “What is the regex to extract all the emojis from a string?,” Stack Overflow, 01-Jun-1962. [Online]. Available: https://stackoverflow.com/questions/24840667/what-is-the-regex-to-extract-all-the-emojis-from-a-string. [Accessed: 07-Mar-2022].

[7] “Remove special characters in the string in Java?,” Stack Overflow, 01-Nov-1961. [Online]. Available: https://stackoverflow.com/questions/21074485/remove-special-characters-in-the-string-in-java. [Accessed: 08-Mar-2022].


