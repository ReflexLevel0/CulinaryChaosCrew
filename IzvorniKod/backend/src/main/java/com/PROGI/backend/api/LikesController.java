package com.PROGI.backend.api;

import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.exceptions.RecipeNotFound;
import com.PROGI.backend.model.Like;
import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping(path = "like")
    public ResponseEntity<?> addLike(@RequestBody @NonNull Like like) {
        try{
            likesService.addLike(like);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "unlike")
    public void deleteLike(@RequestParam @NonNull UUID uid, @RequestParam @NonNull UUID rid) throws RecipeNotFound, ProfileNotFound {
        likesService.deleteLike(uid, rid);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}")
    public List<Recipe> getLikedRecipesForUser(@PathVariable("uid") UUID uid) throws ProfileNotFound {
        return likesService.getLikedRecipesForUser(uid);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/allLikedRecipes")
    public List<Like> getLikedRecipes() {
        return likesService.getAllLikes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "count/{uid}")
    public int likesCount(@PathVariable("uid") UUID rid) {
        return likesService.likesCount(rid);
    }
}