// ISP Practice Exercise — starter code. Contractor is forced to fake paid
// leave and health insurance. Split Employee into focused interfaces instead.
// See README.md in this folder for the full task and requirements.

interface Employee {
    double calculateSalary();
    void takePaidLeave(int days);
    void enrollInHealthInsurance();
}

class Contractor implements Employee {
    public double calculateSalary() {
        return 5000.0; // flat contract rate
    }

    public void takePaidLeave(int days) {
        throw new UnsupportedOperationException("Contractors don't get paid leave");
    }

    public void enrollInHealthInsurance() {
        throw new UnsupportedOperationException("Contractors aren't eligible for company insurance");
    }
}

public class Starter {
    public static void main(String[] args) {
        Employee contractor = new Contractor();
        System.out.println("Salary: " + contractor.calculateSalary());
        contractor.takePaidLeave(2); // 💥 throws at runtime
    }
}
