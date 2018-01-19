package companyStructure;

abstract class BusinessEmployee extends Employee{
private double bonusBudget;


    public BusinessEmployee(String name){
        super(name, 50000);
        bonusBudget=0;

    }

    public double getBonusBudget(){
       return bonusBudget;
    }

    public void setBonusBudget(double bonus){
        this.bonusBudget=bonus;
    }

    @Override
    public String employeeStatus(){
        return super.employeeStatus()+" with a budget of "+getBonusBudget();
    }

}
