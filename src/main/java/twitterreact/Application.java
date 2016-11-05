package twitterreact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitterreact.twitter.Twitter;
import twitterreact.twitter.TwitterClient;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        System.out.println("Starting app..");

//        twitter4jSample();

        rxJavaSample();
    }

    static void rxJavaSample() {
        Twitter.create().
                searchTerms(new String[]{"poland"}).
                statuses().
                take(10).
                forEach(status -> {
                    log.info(status.getText());
                });
    }

    static void twitter4jSample() {
        TwitterClient twitterClient = new TwitterClient();
        twitterClient.sample();
    }
}
