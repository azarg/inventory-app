package com.azargurbanov.inventory_service.outbound;

import com.azargurbanov.inventory_service.item.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "outbound_order")
public class OutboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String customerNumber;

    @Column(nullable = false)
    private Instant orderedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OutboundOrderStatus status;

    @OneToMany(mappedBy = "outboundOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OutboundOrderLine> lines = new ArrayList<>();

    protected OutboundOrder() {
        // required by JPA
    }

    public OutboundOrder(String customerNumber, Instant orderedAt) {
        this.customerNumber = customerNumber;
        this.orderedAt = orderedAt;
        this.status = OutboundOrderStatus.OPEN;
    }

    public void addLine(Item item, int requestedQuantity) {
        lines.add(new OutboundOrderLine(this, item, requestedQuantity));
    }

    public Long getId() {
        return id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public Instant getOrderedAt() {
        return orderedAt;
    }

    public OutboundOrderStatus getStatus() {
        return status;
    }

    public List<OutboundOrderLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OutboundOrder other))
            return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}