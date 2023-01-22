package org.project.entity;
import java.util.ArrayList;

public class Account {
    private final String name;
    private final String uuid;
    private final ArrayList<Transaction> transactions;

    public Account (String name , User holder, Bank theBank){
        this.name = name;
        this.uuid = theBank.getNewAccountUUID();
        this.transactions = new ArrayList<Transaction>();
    }

    public String getUUID() {
        return uuid;
    }

    public String  getSummaryLine() {
        double balance = this.getBalance();
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    public double getBalance() {
        double balance = 0;
        for (Transaction t: this.transactions) {
            balance += t.getMoney();
        }
        return balance;
    }

    public void addTransaction(double amount, String memo) {
        // Создаем новую транзакцию и добавляем в список
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }

    public void showTransactions(){
        for (Transaction t: transactions){
            t.transactionInfo();
        }
    }
}