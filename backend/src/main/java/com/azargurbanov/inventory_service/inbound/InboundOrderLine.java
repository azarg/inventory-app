package com.azargurbanov.inventory_service.inbound;

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
@Table(name = "inbound_order_line")
public class InboundOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inbound_order_id")
    private InboundOrder inboundOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private int quantity;

    protected InboundOrderLine() {
        // required by JPA
    }

    InboundOrderLine(InboundOrder inboundOrder, Item item, int quantity) {
        this.inboundOrder = inboundOrder;
        this.item = item;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public InboundOrder getInboundOrder() {
        return inboundOrder;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof InboundOrderLine other))
            return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}