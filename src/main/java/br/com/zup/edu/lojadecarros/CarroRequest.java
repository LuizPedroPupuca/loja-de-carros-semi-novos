package br.com.zup.edu.lojadecarros;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CarroRequest {

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @Min(2010)
    @Max(2099)
    private Integer ano;

    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @NotBlank
    @Min(17) @Max(17)
    private String chassi;

    public CarroRequest(String marca, String modelo, Integer ano, String placa, String chassi) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.chassi = chassi;
    }

    public CarroRequest() {
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    public String getChassi() {
        return chassi;
    }

    public Carro toModel() {
        return new Carro(marca, modelo, ano, placa, chassi);
    }

}
