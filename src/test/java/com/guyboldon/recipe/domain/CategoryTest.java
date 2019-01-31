package com.guyboldon.recipe.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
        String desc = "hello";
        category.setDescription(desc);
        assertEquals(desc, category.getDescription());
    }

    @Test
    public void getRecipes() {
        String desc = "yum yum";
        Recipe recipe = new Recipe();
        category.getRecipes().add(recipe.setDescription(desc));
        assertEquals(desc, category.getRecipes().stream().findFirst().orElseThrow(RuntimeException::new).getDescription());
    }
}