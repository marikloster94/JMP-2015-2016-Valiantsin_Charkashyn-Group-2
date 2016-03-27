package com.epam.module.jpa.entity;

import javax.persistence.*;


@Entity
@Table(name ="account")
public class Account {

    private int accountID;
    private int number;
    private String name;
    private double money;
    private Person client = new Person();

    @Column (name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column (name="accountNumber")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    @Column (name="money")
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
    @Id
    @Column(name = "accountId")
    @GeneratedValue
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="personId", nullable = false)
	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}
}
