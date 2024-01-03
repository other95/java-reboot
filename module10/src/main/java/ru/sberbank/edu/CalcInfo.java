package ru.sberbank.edu;

public class CalcInfo {
    private Double percentage;
    private Double sum;
    private Integer years;
    private Double result;

    private final Double MIN_SUM = 50000.0;

    public CalcInfo(Double percentage, Double sum, Integer years) {
        this.percentage = percentage;
        this.sum = sum;
        this.years = years;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public void calcResult() {
        result = sum * percentage / 100 * years;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
