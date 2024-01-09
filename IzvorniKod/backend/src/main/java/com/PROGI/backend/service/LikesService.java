package com.PROGI.backend.service;

import com.PROGI.backend.dao.LikesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LikesService {
    private final LikesDao likesDao;

    @Autowired
    public LikesService(@Qualifier("likesDao") LikesDao likesDao) {
        this.likesDao = likesDao;
    }
}