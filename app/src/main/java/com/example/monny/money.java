package com.example.monny;

public class money {
    public String currentExpense;

    public money() {

    }

    public money(String currentExpense){
        this.currentExpense = currentExpense;
    }

    public  String getCurrentExpense() {
        return currentExpense;
    }

    public void setCurrentExpense(String currentExpense) {
        this.currentExpense = currentExpense;
    }

}
