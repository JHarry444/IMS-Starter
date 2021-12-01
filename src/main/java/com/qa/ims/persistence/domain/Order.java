package com.qa.ims.persistence.domain;

public class Order {
    private Long order_id;
    private Long customer_id;

    public Order(Long order_id, Long customer_id) {
        this.setOrderId(order_id);
        this.setCustomerId(customer_id);
    }

    public Long getOrderId() {
        return this.order_id;
    }

    public void setOrderId(Long id) {
        this.order_id = id;
    }

    public Long getCustomerId() {
        return this.customer_id;
    }

    public void setCustomerId(Long id) {
        this.customer_id = id;
    }

    @Override
    public String toString() {
        return "order id:" + order_id + " customer id:" + customer_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
        result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
        return result;
    }

}
