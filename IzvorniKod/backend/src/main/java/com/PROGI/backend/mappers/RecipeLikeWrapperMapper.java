package com.PROGI.backend.mappers;

import com.PROGI.backend.model.Recipe;
import com.PROGI.backend.model.RecipeLikeWrapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeLikeWrapperMapper implements RowMapper<RecipeLikeWrapper> {
    @Override
    public RecipeLikeWrapper mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Recipe r = new RecipeMapper().mapRow(resultSet, rowNum);
        boolean liked;
        try{
            liked = resultSet.getBoolean("liked");
        }catch(Exception ex){
            liked = false;
        }
        return new RecipeLikeWrapper(r.getRecipeId(), r.getUserId(), r.getName(), r.getCategory(), r.getIngredients(), r.getInstructions(), r.getOrigin(), r.getTags(), r.getImageURL(), r.getVideoURL(), r.getPreparationTime(), liked);
    }
}
