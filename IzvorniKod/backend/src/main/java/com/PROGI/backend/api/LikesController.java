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
        try {
            likesService.addLike(like);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        } catch (RecipeNotFound ex) {
            return new ResponseEntity<>("Recipe not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "unlike")
    public ResponseEntity<?> deleteLike(@RequestParam @NonNull UUID uid, @RequestParam @NonNull UUID rid) {
        try {
            likesService.deleteLike(uid, rid);
        } catch (RecipeNotFound ex) {
            return new ResponseEntity<>("Recipe not found!", HttpStatus.NOT_FOUND);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{uid}")
    public ResponseEntity<?> getLikedRecipesForUser(@PathVariable("uid") UUID uid) {
        List<Recipe> list;
        try {
            list = likesService.getLikedRecipesForUser(uid);
        } catch (ProfileNotFound ex) {
            return new ResponseEntity<>("Profile not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/allLikedRecipes")
    public List<Like> getLikedRecipes() {
        return likesService.getAllLikes();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "count/{uid}")
    public ResponseEntity<?> likesCount(@PathVariable("uid") UUID rid) {
        int likesCount;
        try{
            likesCount = likesService.likesCount(rid);
        } catch (RecipeNotFound ex) {
            return new ResponseEntity<>("Recipe not found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(likesCount, HttpStatus.OK);
    }
}