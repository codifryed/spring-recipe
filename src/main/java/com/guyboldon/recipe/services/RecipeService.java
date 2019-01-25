package com.guyboldon.recipe.services;

import com.guyboldon.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

}
