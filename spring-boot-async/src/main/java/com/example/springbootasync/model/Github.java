package com.example.springbootasync.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Github {
    private String current_user_url;
    private String current_user_authorizations_html_url;
    private String code_search_url;
    private String commit_search_url;
    private String emails_url;
    private String emojis_url;
    private String events_url;
}
