package twitterreact.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.StatusAdapter;

class StatusPrinter extends StatusAdapter {

    private static final Logger log = LoggerFactory.getLogger(StatusPrinter.class);

    @Override
    public void onException(Exception ex) {
        log.error("Received exception from Twitter Streaming API");
    }

    @Override
    public void onStatus(Status status) {
        log.info("STATUS (by @" + status.getUser().getName() + ")\n" + status.getText());
    }
}
