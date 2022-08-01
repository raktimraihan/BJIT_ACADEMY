package com.bjit.training.model;

import java.util.Date;

public class ExpenseModel {
	private String name;
	private Date dExpense;
	private Double amount;
	private String descriptiopn;
	private String category;
	
	public ExpenseModel(String name, Date dExpense, Double amount, String descriptiopn, String category) {
		super();
		this.name = name;
		this.dExpense = dExpense;
		this.amount = amount;
		this.descriptiopn = descriptiopn;
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getdExpense() {
		return dExpense;
	}
	public void setdExpense(Date dExpense) {
		this.dExpense = dExpense;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDescriptiopn() {
		return descriptiopn;
	}
	public void setDescriptiopn(String descriptiopn) {
		this.descriptiopn = descriptiopn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "ExpenseModel [name=" + name + ", dExpense=" + dExpense + ", amount=" + amount + ", descriptiopn="
				+ descriptiopn + ", category=" + category + "]";
	} 

}
