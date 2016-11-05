package twitterreact.twitter;

import org.slf4j.Logger;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterClient {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TwitterClient.class);

    public final TwitterStream twitterStream;

    public TwitterClient() {
        TwitterStreamFactory factory = new TwitterStreamFactory(TwitterConfiguration.DEFAULT);
        twitterStream = factory.getInstance();

        twitterStream.addListener(new StatusPrinter());
    }

    public void sample() {
        twitterStream.sample();
    }
}
