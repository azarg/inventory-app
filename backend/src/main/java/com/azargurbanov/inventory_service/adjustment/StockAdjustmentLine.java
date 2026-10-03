package com.azargurbanov.inventory_service.adjustment;

import com.azargurbanov.inventory_service.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock_adjustment_line")
public class StockAdjustmentLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_adjustment_id")
    private StockAdjustment stockAdjustment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false, length = 100)
    private String reason;

    @Column(nullable = false)
    private int quantity;

    protected StockAdjustmentLine() {
        // required by JPA
    }

    StockAdjustmentLine(StockAdjustment stockAdjustment, Item item, String reason, int quantity) {
        this.stockAdjustment = stockAdjustment;
        this.item = item;
        this.reason = reason;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public StockAdjustment getStockAdjustment() {
        return stockAdjustment;
    }

    public Item getItem() {
        return item;
    }

    public String getReason() {
        return reason;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof StockAdjustmentLine other))
            return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}