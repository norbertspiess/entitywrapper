package de.norbertspiess.spring.boot.entitywrapper.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Address {
    @Id
    @GeneratedValue
    public Long id;

    public String street;

    @ElementCollection
    @JoinTable(name = "push_notification_message", joinColumns = {@JoinColumn(name = "push_notification_id")})
    @Column(name = "message")
    @MapKeyColumn(name = "locale")
    public Map<Locale, String> message = new HashMap<>();
}
