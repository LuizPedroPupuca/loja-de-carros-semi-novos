package br.com.zup.edu.lojadecarros;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Carro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String placa;

    @Column(unique = true)
    private String chassi;

    public Carro(String marca, String modelo, Integer ano, String placa, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
    }

    public Carro() {
    }

    public Long getId() {
        return id;
    }
}
