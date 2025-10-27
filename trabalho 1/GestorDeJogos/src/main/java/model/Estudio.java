package com.example.gestordejogos.model;

public class Estudio {
    private int id;
    private String nome;
    private String paisOrigem;

    public Estudio() {}

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPaisOrigem() { return paisOrigem; }
    public void setPaisOrigem(String paisOrigem) { this.paisOrigem = paisOrigem; }

    // Essencial para exibir o nome no ComboBox
    @Override
    public String toString() {
        return getNome();
    }
}