package companyStructure;

import java.util.ArrayList;
import java.util.List;

public class TechnicalLead extends TechnicalEmployee implements Manager {
    private int headCount;
    private List<SoftwareEngineer> reports;
    protected BusinessLead businessLead;


    public TechnicalLead(String name){
        super(name);
        setBaseSalary(super.getBaseSalary()*1.3);
        headCount=4;
        reports=new ArrayList<SoftwareEngineer>();
        businessLead=null;

    }

    public int getHeadCount() {
        return headCount;
    }

    @Override
    public int setHeadCount(int headCount){
        this.headCount=headCount;
        return this.headCount;
    }

    public boolean hasHeadCount(){
       if(reports.size()<this.headCount)
        return true;
       else return false;
    }

    public List<SoftwareEngineer> getReports(){
        return reports;
    }

    /*
    Accepts the reference to a SoftwareEngineer object, and if the TechnicalLead has head count left
    adds this employee to their list of direct reports.
     */
    public boolean addReport(SoftwareEngineer e) {
        if (hasHeadCount()) {
            this.reports.add(e);
            e.manager=this;
            return true;}
         else return false;
    }


    public boolean approveCheckIn(SoftwareEngineer e) {
        if (reports.contains(e) && (e.codeAccess)) {
            return true;
        }
        return false;
    }

    /*
    Checks if the bonus amount requested would be approved by the BusinessLead
     supporting this TechnicalLead. If it is, BusinessLead request for this bonus.
     */
    public boolean requestBonus(Employee e, double bonus){
    if(this==e.getManager()&& businessLead.approveBonus(e,bonus)){
        businessLead.requestBonus(e,bonus);
        return true;
    }
else return false;
    }


    public String getTeamStatus() {
        if (this.reports.size() == 0) {
            return this.employeeStatus() + " and no direct reports yet.";
        } else {
            String result = this.employeeStatus() + " and is managing: \n";
            for (SoftwareEngineer e : reports) {
                result = result + e.employeeStatus()+"\n";
            }
            return result;
        }
    }

}
