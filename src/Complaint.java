class Complaint {
    private String description;
    private String status;

    public Complaint(String description) {
        this.description = description;
        this.status = "Pending";
    }

    public void resolve() {
        status = "Resolved";
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Complaint: " + description + " | Status: " + status;
    }
}
