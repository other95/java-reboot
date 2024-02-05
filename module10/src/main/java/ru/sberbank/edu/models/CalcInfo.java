package ru.sberbank.edu.models;

import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotNull;

public class CalcInfo {

    private static final Integer MIN_SUM = 50000;

    @NotNull(message = "Заполните поле сумма")
    @Min(value = MIN_SUM, message = "Сумма не должна быть менее 50000")
    private Integer sum;
    @NotNull(message = "Заполните поле процент")
    @Min(value = 1, message = "Процент должен быть больше 0" )
    private Integer percentage;
    @NotNull(message = "Заполните поле срок")
    @Min(value = 1, message = "Срок должен быть больше 0" )
    private Integer years;
    private Integer result;

    // Здесь обязательно должен быть безаргументый конструктор (не удалять комментарий!)
    public CalcInfo() {

    }

    public CalcInfo(int sum, int percentage,  int years) {
        this.sum = sum;
        this.percentage = percentage;
        this.years = years;
    }

    public void calcResult() {
        result =  sum * percentage / 100 * years;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
