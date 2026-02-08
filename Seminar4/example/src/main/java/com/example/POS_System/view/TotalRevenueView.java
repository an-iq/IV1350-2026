package com.example.POS_System.view;

import com.example.POS_System.observer.SaleObserver;

public class TotalRevenueView implements SaleObserver {
    private double totalRevenue;

    @Override
    public void update(double totalRevenue) {
        this.totalRevenue = totalRevenue;
        System.out.println("Total Revenue: " + totalRevenue);
    }

}
