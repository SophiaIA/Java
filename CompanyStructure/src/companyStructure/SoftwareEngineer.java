package companyStructure;

public class SoftwareEngineer extends TechnicalEmployee {

    boolean codeAccess;

    public SoftwareEngineer(String name){
        super(name);
        codeAccess=false;

    }

    public boolean getCodeAccess(){
        return codeAccess;
    }

    public void setCodeAccess(boolean access){
        this.codeAccess=access;
    }

    /*
    If this SoftwareEngineer's manager approves of their check in. If the check in is approved, this engineer
     successful checkin count is increased.
     */
    public boolean checkInCode(boolean approved){
        if (approved) {
            this.checkIns=this.checkIns+1;
            return true;
        }
        else {
            codeAccess=false;
            return false;
        }
    }


}
