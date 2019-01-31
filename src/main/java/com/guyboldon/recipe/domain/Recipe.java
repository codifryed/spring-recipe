package com.guyboldon.recipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Recipe setDescription(String description) {
        this.description = description;
        return this;
    }


    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }


    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }


    public void setServings(Integer servings) {
        this.servings = servings;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public void setDirections(String directions) {
        this.directions = directions;
    }


    public void setImage(Byte[] image) {
        this.image = image;
    }


    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.getIngredients().add(ingredient);
        return this;
    }

    public Recipe addIngredient(String description, Double amount, UnitOfMeasure unitOfMeasure) {
        Ingredient ingredient = new Ingredient(description, BigDecimal.valueOf(amount), unitOfMeasure);
        return addIngredient(ingredient);
    }

    public Optional<Recipe> removeIngredient(Ingredient ingredient) {
        return this.getIngredients().remove(ingredient) ? Optional.of(this) : Optional.empty();
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
