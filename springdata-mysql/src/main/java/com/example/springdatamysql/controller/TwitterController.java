package com.example.springdatamysql.controller;


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
    public void getTwitter(){
        TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory("ggdw5iSgOwB2h6yhkjSkRzkfA", "eAndsnika11NElcINGBDCGQ5LHJtsIvjOYeRCQP8ObP7tx6rhX");
        OAuth1Operations oAuth1Operations =connectionFactory.getOAuthOperations();
        OAuthToken requestToken = oAuth1Operations.fetchRequestToken("https://api.twitter.com/oauth/request_token", null);
        OAuthToken accessToken = oAuth1Operations.exchangeForAccessToken(
                new AuthorizedRequestToken(requestToken, ""), null
        );
        System.out.println("Token Value:-accesstoken");
        accessToken.getSecret();
        accessToken.getValue();
        Twitter twitter =new TwitterTemplate(
                "ggdw5iSgOwB2h6yhkjSkRzkfA",
                "eAndsnika11NElcINGBDCGQ5LHJtsIvjOYeRCQP8ObP7tx6rhX",
                accessToken.getValue(),
                accessToken.getSecret());
        TwitterProfile profile =twitter.userOperations().getUserProfile();
        System.out.println(profile.toString());
    }

    @GetMapping("/oauth2/authorize/normal/twitter")
    public void twitterOauthLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TwitterConnectionFactory connectionFactory =
                new TwitterConnectionFactory("<client Key>","<client secret>");
        OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
        oauthOperations.toString();
        OAuthToken requestToken = oauthOperations.fetchRequestToken("https://api.twitter.com/oauth/request_token", null);
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(String.valueOf(requestToken), OAuth1Parameters.NONE);
        response.sendRedirect(authorizeUrl);
    }

}
