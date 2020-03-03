public enum Abonnement {
    BASIC("Abonnement Basic"),
    PREMIUM("Abonnement Premium"),
    VIP("Abonnement VIP");

    private String name = "";

    Abonnement(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}