package com.azargurbanov.inventory_service.inbound;

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
@Table(name = "inbound_order")
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String supplierNumber;

    @Column(nullable = false)
    private Instant receivedAt;

    @OneToMany(mappedBy = "inboundOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InboundOrderLine> lines = new ArrayList<>();

    protected InboundOrder() {
        // required by JPA
    }

    public InboundOrder(String supplierNumber, Instant receivedAt) {
        this.supplierNumber = supplierNumber;
        this.receivedAt = receivedAt;
    }

    public void addLine(Item item, int quantity) {
        lines.add(new InboundOrderLine(this, item, quantity));
    }

    public Long getId() {
        return id;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }

    public List<InboundOrderLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof InboundOrder other))
            return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}