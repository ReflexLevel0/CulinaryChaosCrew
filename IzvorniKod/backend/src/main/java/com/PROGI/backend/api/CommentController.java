package com.PROGI.backend.api;

import com.PROGI.backend.exceptions.CommentNotFound;
import com.PROGI.backend.exceptions.ProfileNotFound;
import com.PROGI.backend.exceptions.RecipeNotFound;
import com.PROGI.backend.model.Comment;
import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/comment")
@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) { this.commentService = commentService; }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "")
    public ResponseEntity<?> addComment(@RequestBody @NonNull Comment comment) {
        try{
            comment.setDate(new Timestamp(comment.getTimestamp().getTime() - comment.getTimestamp().getTime() % 1000));
            commentService.addComment(comment);
        }catch(RecipeNotFound | ProfileNotFound ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "")
    public ResponseEntity<?> deleteLike(@RequestParam @NonNull UUID uid, @RequestParam @NonNull UUID rid, @RequestParam @NonNull long timestamp) {
        try{
            Timestamp timestamp1 = new Timestamp(timestamp - timestamp % 1000);
            commentService.deleteComment(uid, rid, timestamp1);
        }catch(CommentNotFound ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "{rid}")
    public ResponseEntity<?> getCommentsByRecipeId(@PathVariable("rid") UUID recipeId) {
        List<Comment> comments;
        try{
            comments = commentService.getComments(recipeId);
        }catch(RecipeNotFound ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
