package com.project.CaseKey;

public interface OpenIDAuth {
    String login(String callbackUrl);
    String verify(String receivingUrl, String responseQuery);
}
