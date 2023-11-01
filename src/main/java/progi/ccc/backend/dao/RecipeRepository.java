package progi.ccc.backend.dao;

import progi.ccc.backend.domain.Profile;
import progi.ccc.backend.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByRecipeName(String recipeName);

    Optional<Recipe> findByRecipeID(long recipeID);
    //public Optional<List<Recipe>> findByUserID(String userID);
    //ispraviti, cilj je dobiti cijelu listu recepata pojedinog korisnika

    int countByRecipeName(String recipeName);

    // Note: exists- query is the best if we just want to predict conflicts
    //boolean existsByUserNameAndUserIDNot(String recipeName, Long userID);

}