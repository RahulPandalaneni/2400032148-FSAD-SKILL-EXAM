package com.klef.fsad.exam;

import javax.persistence.*;
import java.util.Date;

/**
 * Service Entity Class
 * Maps to the 'service' table in the 'fsadexam' MySQL database.
 *
 * Package : com.klef.fsad.exam
 */
@Entity
@Table(name = "service")
public class Service {

    // ── Primary Key (Auto-Generated) ──────────────────────────────────────────────
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int id;

    // ── Core Attributes ───────────────────────────────────────────────────────────
    @Column(name = "service_name", nullable = false, length = 100)
    private String name;

    @Column(name = "service_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "service_status", length = 50)
    private String status;

    // ── Additional Relevant Attributes ────────────────────────────────────────────
    @Column(name = "service_type", length = 100)
    private String serviceType;

    @Column(name = "service_cost")
    private double cost;

    @Column(name = "service_duration", length = 50)
    private String duration;

    @Column(name = "service_provider", length = 100)
    private String provider;

    @Column(name = "service_location", length = 150)
    private String location;

    // ── Default Constructor ────────────────────────────────────────────────────────
    public Service() {
        super();
    }

    // ── Parameterised Constructor ──────────────────────────────────────────────────
    public Service(String name, Date date, String status, String serviceType,
                   double cost, String duration, String provider, String location) {
        this.name        = name;
        this.date        = date;
        this.status      = status;
        this.serviceType = serviceType;
        this.cost        = cost;
        this.duration    = duration;
        this.provider    = provider;
        this.location    = location;
    }

    // ── Getters & Setters ──────────────────────────────────────────────────────────
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    // ── toString ──────────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "\nService {"
             + "\n  ID          = " + id
             + "\n  Name        = " + name
             + "\n  Date        = " + date
             + "\n  Status      = " + status
             + "\n  Type        = " + serviceType
             + "\n  Cost        = " + cost
             + "\n  Duration    = " + duration
             + "\n  Provider    = " + provider
             + "\n  Location    = " + location
             + "\n}";
    }
}
