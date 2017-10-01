package de.norbertspiess.spring.boot.data.wrapper;

import de.norbertspiess.spring.boot.data.Address;

public class AddressWrapper<T extends Address> {
    private T entity;

    public AddressWrapper(T entity) {
        // constructor for jpa / hibernate
        this.entity = entity;
    }

    public AddressWrapper() {
        this.entity = (T) new Address();
    }

    public String getStreet() {
        return this.entity.street + " and stuff";
    }

    // TODO should be package private or somehow protected
    public Address getEntity() {
        return entity;
    }

    public void setStreet(String street) {
        this.entity.street = "fancy " + street;
    }

    public Long getId() {
        return entity.id;
    }
}
