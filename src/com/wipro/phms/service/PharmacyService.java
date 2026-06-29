package com.wipro.phms.service;
import java.time.LocalDate;

import java.util.ArrayList;

import com.wipro.phms.entity.Customer;
import com.wipro.phms.entity.Medicine;
import com.wipro.phms.entity.Purchase;
import com.wipro.phms.util.InvalidCustomerException;
import com.wipro.phms.util.MedicineNotAvailableException;
import com.wipro.phms.util.PurchaseException;


public class PharmacyService {
    private ArrayList<Customer> customers;
    private ArrayList<Medicine> medicines;
    private ArrayList<Purchase> purchases;
    public PharmacyService(ArrayList<Customer> customers,ArrayList<Medicine> medicines,ArrayList<Purchase> purchases) {
    	this.customers=customers;
    	this.medicines=medicines;
    	this.purchases=purchases;	
    }
    public boolean validateCustomer(String customerId) 
    	throws InvalidCustomerException{
    	for(Customer customer:customers) {
    		if(customer.getCustomerId().equals(customerId)) {
    			return true;
    		}
    	}
    	throw new InvalidCustomerException();
    }
    public Medicine validateMedicine(String medicineId, int qty)
            throws MedicineNotAvailableException {
        for (Medicine m : medicines) {
            if (m.getMedicineId().equals(medicineId)) {
                if (m.getQuantityAvailable() < qty) {
                    throw new MedicineNotAvailableException();
                }
                LocalDate expiry = LocalDate.parse(m.getExpiryDate());
                if (expiry.isBefore(LocalDate.now())) {
                    throw new MedicineNotAvailableException();
                }
                return m;
            }
        }

        throw new MedicineNotAvailableException();
    }
    public Purchase createPurchase(String customerId, String medicineId, int qty)
            throws Exception {
        if (qty <= 0) {
            throw new PurchaseException();
        }
        validateCustomer(customerId);
        Medicine medicine = validateMedicine(medicineId, qty);
        medicine.setQuantityAvailable(medicine.getQuantityAvailable() - qty);
        double totalAmount = medicine.getPrice() * qty;
        String purchaseId = "P00" + (purchases.size() + 1);
        Purchase purchase = new Purchase(purchaseId, customerId, medicineId, qty, totalAmount);
        purchases.add(purchase);
        return purchase;
    }
    public void printPurchaseHistory(String customerId) {
        for (Purchase p : purchases) {
            if (p.getCustomerId().equals(customerId)) {
                for (Medicine m : medicines) {
                    if (m.getMedicineId().equals(p.getMedicineId())) {
                        System.out.println("Purchase ID : " + p.getPurchaseId());
                        System.out.println("Medicine    : " + m.getName());
                        System.out.println("Quantity    : " + p.getQuantity());
                        System.out.println("Amount      : Rs." + p.getTotalAmount());
                    }
                }
            }
        }
    }
}
