package com.lepric.btservice.payload.response;

import lombok.Data;

@Data
public class UpdatePasswordModelHelper {
    String oldPassword;
    String newPassword;
    String newPasswordAgain;
    public UpdatePasswordModelHelper() {
    }
    public UpdatePasswordModelHelper(String oldPassword, String newPassword, String newPasswordAgain) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordAgain = newPasswordAgain;
    }
}
