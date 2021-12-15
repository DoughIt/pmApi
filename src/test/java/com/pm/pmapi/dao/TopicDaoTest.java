package com.pm.pmapi.dao;

import com.pm.pmapi.dto.TopicInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TopicDaoTest {
    @Autowired
    private TopicDao topicDao;
    @Test
    void listChildrenByParentId() {
    }

    @Test
    void listTopicByFilterType() {
    }

    @Test
    void listTopicByUserId() {
    }

    @Test
    void getTopicInfoById() {
        TopicInfo info = topicDao.getTopicInfoById(1L);
        Assertions.assertEquals(info.getId(),1);
    }
}