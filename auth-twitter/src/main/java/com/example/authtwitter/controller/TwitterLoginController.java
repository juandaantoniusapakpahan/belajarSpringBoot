package com.example.authtwitter.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;


@Controller
public class TwitterController {


    @GetMapping("/oauth2/callback/twitter")
    public void getTwitter() {

        TwitterConnectionFactory connectionFactory =
                new TwitterConnectionFactory("BNNNrKVjeT3KibLNSnpbFRPbp","nRkzaS569JM9Xo2Ey8X3bJAtOwRezblYRMDjEDg0ZumOVhxmD4");
        OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuthToken requestToken = oauthOperations.fetchRequestToken("https://api.twitter.com/oauth/request_token", null);

        OAuthToken accessToken = oauthOperations.exchangeForAccessToken(
                new AuthorizedRequestToken(requestToken, ""), null);
        System.out.println("Token Value:- accesstoken");
        accessToken.getSecret();
        accessToken.getValue();
        Twitter twitter = new TwitterTemplate("BNNNrKVjeT3KibLNSnpbFRPbp",
                "nRkzaS569JM9Xo2Ey8X3bJAtOwRezblYRMDjEDg0ZumOVhxmD4",
                accessToken.getValue(),
                accessToken.getSecret());
        TwitterProfile profile = twitter.userOperations().getUserProfile();
        System.out.println(profile.toString());

    }


    @GetMapping("/oauth2/authorize/normal/twitter")
    public void twitterOauthLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TwitterConnectionFactory connectionFactory =
                new TwitterConnectionFactory("BNNNrKVjeT3KibLNSnpbFRPbp","nRkzaS569JM9Xo2Ey8X3bJAtOwRezblYRMDjEDg0ZumOVhxmD4");
        OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
        oauthOperations.toString();
        OAuthToken requestToken = oauthOperations.fetchRequestToken("http://localhost:8080/oauth2/callback/twitter", null);        String authorizeUrl = oauthOperations.buildAuthorizeUrl(String.valueOf(requestToken), OAuth1Parameters.NONE);
        response.sendRedirect(authorizeUrl);
    }
}