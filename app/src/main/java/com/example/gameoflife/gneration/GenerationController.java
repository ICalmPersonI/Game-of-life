package com.example.gameoflife.gneration;

public class GenerationController {

    private final Generation generation;
    private final GenerationView view;
    private Cell[][] currentGeneration;

    public GenerationController(Generation generation, GenerationView view) {
        this.generation = generation;
        this.view = view;
        this.currentGeneration = generation.getOldGen();
    }

    public void generateGeneration() {
        generation.createNewGeneration();
        currentGeneration = generation.getNewGen();
        generation.setOldGen(currentGeneration);
    }

    public void updateView() {
        view.update(generation.getNewGen());
    }
}
