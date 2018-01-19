package companyStructure;

public class Accountant extends BusinessEmployee{

private TechnicalLead techLead;
private double bonusBudget;

    public Accountant(String name){
        super(name);
        techLead=null;
        bonusBudget=0;
    }

    public TechnicalLead getTeamSupported(){
        return techLead;
    }

/*
Allows reference to a TechnicalLead to be passed in and saved. Once this happens the Accountant's bonus budget is updated
to be the total of each SoftwareEngineer's base salary that reports to that TechnicalLead plus 10%.
*/
    public void supportTeam(TechnicalLead lead){
        this.techLead=lead;
        for (SoftwareEngineer e:lead.getReports()
                ) {
            bonusBudget=bonusBudget+(e.getBaseSalary()*1.1);
        }
        setBonusBudget(bonusBudget);
    }

    @Override
    public String toString(){
        if(techLead==null) return super.toString()+" with a budget of "+getBonusBudget();
        else return super.toString()+" with a budget of "+getBonusBudget()+" and is supporting "+techLead.getName();
    }

    @Override
    public String employeeStatus(){
        return toString();
    }

    public boolean approveBonus(double bonus){
        if(techLead==null||this.getBonusBudget()<bonus){
            return false;}
            else return true;
    }
}
