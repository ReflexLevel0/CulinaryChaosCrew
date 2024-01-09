package com.PROGI.backend.api;

import com.PROGI.backend.model.Like;
import com.PROGI.backend.service.LikesService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/likes")
@RestController
public class LikesController {
    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService){ this.likesService = likesService; }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "add")
    public void addLike(@NonNull @RequestBody Like like) {
        throw new NotImplementedException();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "delete")
    public void deleteLike(@NonNull @RequestBody Like like) {
        throw new NotImplementedException();
    }
}