package com.PROGI.backend.exceptions;

public class RecipeSearchEmpty extends Exception {
    public RecipeSearchEmpty() { super("No such recipe exists"); }
}
