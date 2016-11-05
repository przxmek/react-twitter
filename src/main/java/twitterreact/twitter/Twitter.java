package twitterreact.twitter;

import rx.Observable;
import twitter4j.Status;
import twitter4j.TwitterStreamFactory;

public class Twitter {
    private static final twitter4j.TwitterStream twitterStream;

    static {
        TwitterStreamFactory factory = new TwitterStreamFactory(TwitterConfiguration.DEFAULT);
        twitterStream = factory.getInstance();
    }

    public static Twitter create() {
        return new Twitter();
    }

    public static Observable<Status> statusesOf(final String[] languages, final String[] searchTerms, final long[] usersToFollow) {
        return Observable.create(new StatusSubscriber(twitterStream, languages, searchTerms, usersToFollow)).share();
    }

    private String[] languagesFilter;
    private String[] searchTermsFilter;
    private long[] usersToFollowFilter;

    public Twitter languages(final String[] languages) {
        languagesFilter = languages;
        return this;
    }

    public Twitter searchTerms(final String[] searchTerms) {
        searchTermsFilter = searchTerms;
        return this;
    }

    public Twitter usersToFollow(final long[] usersToFollow) {
        usersToFollowFilter = usersToFollow;
        return this;
    }

    public Observable<Status> statuses() {
        return Observable.create(new StatusSubscriber(twitterStream, languagesFilter, searchTermsFilter, usersToFollowFilter)).share();
    }
}
