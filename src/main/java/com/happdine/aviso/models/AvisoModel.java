package com.happdine.aviso.models;

import jakarta.persistence.*;

@Table(name="avisos")
@Entity
public class AvisoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_aviso;

    @Column(name = "recado", nullable = false)
    private String recado;

    @Column(name = "timestampp", nullable = false)
    private String timestampp;

    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

    @Lob
    @Column(name = "imagem", columnDefinition = "LONGBLOB")
    private byte[] imagem;

    public Long getId_aviso() {
        return id_aviso;
    }

    public String getRecado() {
        return recado;
    }

    public String getTimestampp() {
        return timestampp;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }

    public void setTimestampp(String timestampp) {
        this.timestampp = timestampp;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}

