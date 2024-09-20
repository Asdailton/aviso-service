package com.happdine.aviso.models;

public enum Categoria {
    ATENCAO("categoria"), ESTRELA("categoria"), MENSAGEM("categoria");
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    Categoria(String categoria) {
        this.categoria = categoria;
    }
}
