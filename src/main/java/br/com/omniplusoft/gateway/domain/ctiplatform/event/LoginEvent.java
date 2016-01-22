package br.com.omniplusoft.gateway.domain.ctiplatform.event;

/**
 * Created by hermeswaldemarin on 14/12/15.
 */
public class LoginEvent extends CTIEvent {

    private String userName;
    private String userPassword;
    private String userAdmin;
    private String passwordAdmin;
    private String serviceName;
    private String terminalNumber;
    private String agentNumber;
    private String group;

    public LoginEvent() {
        super();
    }

    public LoginEvent(String userName, String userPassword, String userAdmin, String passwordAdmin, String serviceName, String terminalNumber, String agentNumber, String group) {
        super();
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAdmin = userAdmin;
        this.passwordAdmin = passwordAdmin;
        this.serviceName = serviceName;
        this.terminalNumber = terminalNumber;
        this.agentNumber = agentNumber;
        this.group = group;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
