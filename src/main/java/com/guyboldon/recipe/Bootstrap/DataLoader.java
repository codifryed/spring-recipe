package com.guyboldon.recipe.Bootstrap;

import com.guyboldon.recipe.domain.*;
import com.guyboldon.recipe.repositories.CategoryRepository;
import com.guyboldon.recipe.repositories.RecipeRepository;
import com.guyboldon.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @EventListener
    public void onContextRefresh(ContextRefreshedEvent contextRefreshedEvent) {

        UnitOfMeasure eachUOM = unitOfMeasureRepository.findByDescription("ea.").orElseThrow(
                () -> new RuntimeException("ea. UOM not found.")
        );
        UnitOfMeasure teaspoonUOM = unitOfMeasureRepository.findByDescription("Teaspoon").orElseThrow(
                () -> new RuntimeException("Teaspoon UOM not found.")
        );
        UnitOfMeasure tablespoonUOM = unitOfMeasureRepository.findByDescription("Tablespoon").orElseThrow(
                () -> new RuntimeException("Tablespoon UOM not found.")
        );

        Recipe recipeGuacamole = new Recipe();
        recipeGuacamole.setDescription("Perfect Guacomole");
        Category catMexican = categoryRepository.findByDescription("Mexican").orElseThrow(
                () -> new RuntimeException("Mexican Category Not found")
        );
        recipeGuacamole.getCategories().add(catMexican);
        // not needed due the fact that we have a many to many relationship here, also it's already added in the table
        // from the recipe entity
//        catMexican.getRecipes().add(recipeGuacamole);

        recipeGuacamole.setCookTime(0);
        recipeGuacamole.setPrepTime(10);
        recipeGuacamole.setServings(4);
        recipeGuacamole.setSource("simplyrecipes");
        recipeGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipeGuacamole.setDifficulty(Difficulty.EASY);

        recipeGuacamole.addIngredient("ripe avocados", 2.0, eachUOM);
        recipeGuacamole.addIngredient("Kosher Salt", 0.5, teaspoonUOM);

        recipeGuacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl. \n2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.) \n3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

        Notes notesguac = new Notes();
        notesguac.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
        recipeGuacamole.setNotes(notesguac);

        recipeRepository.save(recipeGuacamole);

        Recipe recipeTacos = new Recipe();
        recipeTacos.setDescription("Spicy Grilled Chicken Tacos");
        Category catGrill = categoryRepository.findByDescription("grill").orElseThrow(
                () -> new RuntimeException("grill Category not found.")
        );
        recipeTacos.getCategories().add(catGrill);

        recipeTacos.setServings(6);
        recipeTacos.setDifficulty(Difficulty.EASY);
        recipeTacos.setSource("simplyrecipes");
        recipeTacos.setPrepTime(20);
        recipeTacos.setCookTime(15);
        recipeTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        recipeTacos.addIngredient("ancho chili powder", 2.0, tablespoonUOM);

        recipeTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes notesTacos = new Notes();
        notesTacos.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        recipeTacos.setNotes(notesTacos);

        recipeRepository.save(recipeTacos);


    }
}
