/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author ANPHUOC
 */
public class UserError {

   private String userIDError = "";
    private String fulllNameError = "";
    private String roleIDError = "";
    private String passwordError = "";
    private String confirmError = "";
    private String error = "";
    public UserError() {
    }

    public UserError(String userIDError, String fulllNameError, String roleIDError, String passwordError, String confrimError, String error) {
        this.userIDError = userIDError;
        this.fulllNameError = fulllNameError;
        this.roleIDError = roleIDError;
        this.passwordError = passwordError;
        this.confirmError = confrimError;
        this.error = error;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFulllNameError() {
        return fulllNameError;
    }

    public void setFulllNameError(String fulllNameError) {
        this.fulllNameError = fulllNameError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
