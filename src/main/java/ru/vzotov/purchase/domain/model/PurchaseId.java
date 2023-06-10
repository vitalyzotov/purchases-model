package ru.vzotov.purchase.domain.model;

import org.apache.commons.lang3.Validate;
import ru.vzotov.ddd.shared.ValueObject;

import java.util.Objects;
import java.util.UUID;

public class PurchaseId implements ValueObject<PurchaseId> {

    /**
     * Business key
     */
    private String value;

    public PurchaseId(String value) {
        Validate.notNull(value);
        this.value = value;
    }

    public static PurchaseId nextId() {
        return new PurchaseId(UUID.randomUUID().toString());
    }

    public String value() {
        return value;
    }

    @Override
    public boolean sameValueAs(PurchaseId that) {
        return that != null && Objects.equals(value, that.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseId that = (PurchaseId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    protected PurchaseId() {
        //for Hibernate
    }
}
