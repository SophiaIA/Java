package companyStructure;

public class Employee {

    private String name;
    private double baseSalary;
    private static int count=0;
    private int ID;
    protected Employee manager;
    private double currentBonus;


public Employee(String name, double baseSalary){
    this.name=name;
    this.baseSalary=baseSalary;
    count++;
    ID=count;
    currentBonus=0;
}

    public double getBaseSalary(){
        return baseSalary;}

    public void setBaseSalary(double baseSalary){
    this.baseSalary=baseSalary;
    }

    public String getName() {
    return name;
}

    public int getEmployeeID(){return ID;};

    public String toString() {
    return ID+" "+name;
}

    public String employeeStatus(){
    return toString();
    }

    public double getCurrentBonus(){
        return currentBonus;
    }

    public void setCurrentBonus(double currentBonus){
        this.currentBonus=currentBonus;
    }

    public Employee getManager(){
     return this.manager;
    }

    public boolean equals(Employee other){
    return ID==other.ID;
}

}


