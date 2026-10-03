package com.azargurbanov.inventory_service.outbound;

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
@Table(name = "outbound_order_line")
public class OutboundOrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "outbound_order_id")
    private OutboundOrder outboundOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private int requestedQuantity;

    @Column(nullable = false)
    private int fulfilledQuantity;

    protected OutboundOrderLine() {
        // required by JPA
    }

    OutboundOrderLine(OutboundOrder outboundOrder, Item item, int requestedQuantity) {
        this.outboundOrder = outboundOrder;
        this.item = item;
        this.requestedQuantity = requestedQuantity;
    }

    public Long getId() {
        return id;
    }

    public OutboundOrder getOutboundOrder() {
        return outboundOrder;
    }

    public Item getItem() {
        return item;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getFulfilledQuantity() {
        return fulfilledQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OutboundOrderLine other))
            return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}