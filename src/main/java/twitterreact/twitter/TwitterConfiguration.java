package twitterreact.twitter;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

class TwitterConfiguration {
    static final Configuration DEFAULT;

    private static final String CONSUMER_KEY = "AfDGFRKubSy6F88GlU1CxjI82";
    private static final String CONSUMER_SECRET = "PP0VVaI2wVHZEgxlKR5dRIuw3FnvVpbtiMrRU47TP1nQd12Lxi";
    private static final String ACCESS_TOKEN = "794548800006344711-e46MWMHul3yhN6YGxrJatiMCQms98Uf";
    private static final String ACCESS_TOKEN_SECRET = "llaUqYRTmccU2SvwYcpZeATuDMfWmpKULEpxCqbthDWQE";

    static {
        DEFAULT = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
                .build();
    }
}
