package progi.ccc.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import progi.ccc.backend.domain.Recipe;
import progi.ccc.backend.service.RecipeService;

import java.util.List;
@RestController
@RequestMapping("/recepies")
@Service
public class RecepiesController {

        @Autowired
        private RecipeService recipeService;

        @GetMapping("")
        public List<Recipe> listRecipes() {
            return recipeService.listAll();
        }

        @GetMapping("/{id}")
        public Recipe getProfile(@PathVariable("id") long id) {
            return recipeService.fetch(id);
        }

}
