package com.aydsii.tp2.dto;

import java.util.Map;

public class FrankfurterResponseDTO {
    // Para mapear la respuesta json cruda que viene de Frankfurter

    private Double amount;
    private String base;
    private String date;
    private Map<String, Double> rates;

    public FrankfurterResponseDTO() {}

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getBase() { return base; }
    public void setBase(String base) { this.base = base; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Map<String, Double> getRates() { return rates; }
    public void setRates(Map<String, Double> rates) { this.rates = rates; }
}
