package com.project.CaseKey;

import com.project.CaseKey.JsonModel.UserInfo;
import com.project.CaseKey.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.fail;
@SpringBootTest(classes = UserService.class)
public class UserTest {

    private final SteamOpenID openid = new SteamOpenID();
    @Autowired
    private UserService userService;
    private final String  RESPONSE_QUERY = "openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.mode=id_res&openid.op_endpoint=https%3A%2F%2Fsteamcommunity.com%2Fopenid%2Flogin&openid.claimed_id=https%3A%2F%2Fsteamcommunity.com%2Fopenid%2Fid%2F76561198042001813&openid.identity=https%3A%2F%2Fsteamcommunity.com%2Fopenid%2Fid%2F76561198042001813&openid.return_to=http%3A%2F%2Flocalhost%3A8080%2Fauthenticate&openid.response_nonce=2020-10-31T15%3A18%3A15ZtpYtz93ipQu0YepzO8kU0jaksEw%3D&openid.assoc_handle=1234567890&openid.signed=signed%2Cop_endpoint%2Cclaimed_id%2Cidentity%2Creturn_to%2Cresponse_nonce%2Cassoc_handle&openid.sig=pBjfd%2FQ%2B12fOjZcCX48hVH%2BO35w%3D";

    @Test
    void authenticate() {
        openid.login("http://localhost:8080/authenticate");
        if(openid.verify("http://localhost:8080/authenticate", RESPONSE_QUERY) == null);
            fail("Should authenticate correctly");
    }

    @Test
    void getUserInformation() {
        UserInfo userInfo = userService.getUserInformationFromApi("76561198042001813");
        Assertions.assertEquals("Z-Boku", userInfo.getPersonaName());
    }
}
