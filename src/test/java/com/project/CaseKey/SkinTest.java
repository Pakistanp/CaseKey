package com.project.CaseKey;

import com.project.CaseKey.Service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SkinService.class)
public class SkinTest {
    @Autowired
    private SkinService skinService;
}
