package model;

public enum Abonnement {
    BASIC,
    PREMIUM,
    VIP;

    public String name() {
        return this.toString();
    }
}