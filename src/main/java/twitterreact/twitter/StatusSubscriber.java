package twitterreact.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;

import java.util.concurrent.atomic.AtomicReference;

public class StatusSubscriber extends StatusAdapter implements Observable.OnSubscribe<Status> {

    private static final Logger log = LoggerFactory.getLogger(StatusSubscriber.class);

    private final FilterQuery twitterFilterQuery;
    private final TwitterStream twitterStream;
    private AtomicReference<Subscriber<? super Status>> subscriberRef = new AtomicReference<>(null);

    public StatusSubscriber(TwitterStream twitterStream, String[] languages, String[] searchTerms, long[] usersToFollow) {
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.language(languages);
        filterQuery.track(searchTerms);
        filterQuery.follow(usersToFollow);

        this.twitterFilterQuery = filterQuery;
        this.twitterStream = twitterStream;
    }

    @Override
    public void onException(Exception ex) {
        Subscriber<? super Status> subscriber = subscriberRef.get();
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            log.error("Received exception from Twitter Streaming API", ex);
            subscriber.onError(ex);
        }
        cleanup();
    }

    @Override
    public void onStatus(Status status) {
        Subscriber<? super Status> subscriber = subscriberRef.get();
        if (subscriber.isUnsubscribed()) {
            cleanup();
        } else {
            subscriber.onNext(status);
        }
    }

    @Override
    public void call(Subscriber<? super Status> subscriber) {
        if (!subscriberRef.compareAndSet(null, subscriber)) {
            return;
        }

        twitterStream.addListener(this);
        twitterStream.filter(twitterFilterQuery);
    }

    private void cleanup() {
        log.info("cleanup()");

        if (twitterStream != null)
            twitterStream.shutdown();

        Subscriber<? super Status> subscriber = subscriberRef.get();
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.onCompleted();
            subscriber.unsubscribe();
        }
    }
}
