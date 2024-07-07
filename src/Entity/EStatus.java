package Entity;

public enum EStatus {
    CANCEL("Cancel"), PENDING("Pending"), PAID("Paid"), COMPLETE("Completed");
    private String status;
    EStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
//    public static EStatus getEStatus(String status) {
//        return switch (status) {
//            case "Cancel" -> C;
//            case "Pending" -> PD;
//            case "Paid" -> P;
//            case "Completed" -> CP;
//            default -> null;
//        };
//    }
}
