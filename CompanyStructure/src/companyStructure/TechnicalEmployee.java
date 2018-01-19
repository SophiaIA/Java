package companyStructure;

public abstract class TechnicalEmployee extends Employee{
    int checkIns;

    public TechnicalEmployee(String name){
        super(name,75000);
        this.checkIns=0;
    }

    public int getSuccessfulCheckIns(){
        return this.checkIns;
    }

    @Override
    public String employeeStatus(){
        return super.employeeStatus()+" has "+getSuccessfulCheckIns()+" successful Check-Ins";
    }

}
