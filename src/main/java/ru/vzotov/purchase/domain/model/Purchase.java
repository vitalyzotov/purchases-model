package ru.vzotov.purchase.domain.model;

import ru.vzotov.cashreceipt.domain.model.ReceiptId;
import ru.vzotov.cashreceipt.domain.model.PurchaseCategory;
import ru.vzotov.ddd.shared.AggregateRoot;
import ru.vzotov.ddd.shared.Entity;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import ru.vzotov.domain.model.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@AggregateRoot
public class Purchase implements Entity<Purchase> {

    private PurchaseId purchaseId;

    private ReceiptId receiptId;

    private String name;

    private LocalDateTime dateTime;

    private Money price;

    private BigDecimal quantity;

    private PurchaseCategory category;

    public Purchase(PurchaseId purchaseId, String name, LocalDateTime dateTime, Money price, BigDecimal quantity) {
        this(purchaseId, name, dateTime, price, quantity, null, null);
    }

    public Purchase(PurchaseId purchaseId, String name, LocalDateTime dateTime, Money price, BigDecimal quantity,
                    ReceiptId receiptId, PurchaseCategory category) {
        Validate.notNull(purchaseId);
        Validate.notNull(name);
        Validate.notNull(dateTime);
        Validate.notNull(price);
        Validate.notNull(quantity);

        this.purchaseId = purchaseId;
        this.name = name;
        this.dateTime = dateTime;
        this.price = price;
        this.quantity = quantity;

        this.receiptId = receiptId;
        this.category = category;
    }

    public PurchaseId purchaseId() {
        return purchaseId;
    }

    public ReceiptId receiptId() {
        return receiptId;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Money price() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public BigDecimal quantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public PurchaseCategory category() {
        return category;
    }

    public Money sum() {
        return price.multiply(quantity.doubleValue());
    }

    public void assignReceipt(ReceiptId receiptId) {
        this.receiptId = receiptId;
    }

    public void assignCategory(PurchaseCategory category) {
        this.category = category;
    }

    @Override
    public boolean sameIdentityAs(Purchase other) {
        return other != null && new EqualsBuilder()
                .append(purchaseId, other.purchaseId)
                .isEquals();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Purchase that = (Purchase) o;
        return sameIdentityAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId);
    }

    protected Purchase() {
        //for Hibernate
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseId=" + purchaseId +
                ", receiptId=" + receiptId +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    /**
     * Surrogate key
     */
    private Long id;

}
