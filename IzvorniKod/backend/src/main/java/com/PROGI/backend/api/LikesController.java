package com.PROGI.backend.api;

import com.PROGI.backend.model.Like;
import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.service.LikesService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/likes")
@RestController
public class LikesController {
    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "")
    public void addLike(@RequestBody @NonNull Like like) {
        likesService.addLike(like);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "")
    public void deleteLike(@RequestParam @NonNull UUID uid, @RequestParam @NonNull UUID rid) {
        likesService.deleteLike(uid, rid);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getForUser/{uid}")
    public List<Recipe> getLikedRecipes(@PathVariable("uid") UUID uid) {
        return likesService.getLikedRecipes(uid);
    }
}