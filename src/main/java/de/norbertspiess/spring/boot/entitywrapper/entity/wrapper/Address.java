package de.norbertspiess.spring.boot.entitywrapper.entity.wrapper;

import de.norbertspiess.spring.boot.entitywrapper.entity.AddressEntity;

public class Address<T extends AddressEntity> {
    private T entity;

    public Address(T entity) {
        this.entity = entity;
    }

    public String getStreet() {
        return this.entity.getStreet() + " and stuff";
    }
}
