package com.azargurbanov.inventory_service.adjustment;

import com.azargurbanov.inventory_service.item.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "stock_adjustment")
public class StockAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant adjustedAt;

    @OneToMany(mappedBy = "stockAdjustment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockAdjustmentLine> lines = new ArrayList<>();

    protected StockAdjustment() {
        // required by JPA
    }

    public StockAdjustment(Instant adjustedAt) {
        this.adjustedAt = adjustedAt;
    }

    public void addLine(Item item, String reason, int quantity) {
        lines.add(new StockAdjustmentLine(this, item, reason, quantity));
    }

    public Long getId() {
        return id;
    }

    public Instant getAdjustedAt() {
        return adjustedAt;
    }

    public List<StockAdjustmentLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof StockAdjustment other))
            return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}