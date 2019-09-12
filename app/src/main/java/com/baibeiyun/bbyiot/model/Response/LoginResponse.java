package com.baibeiyun.bbyiot.model.Response;

public class LoginResponse {


    /**
     * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1NTkzNzcxODkwOTQsImV4cCI6MTU2ODAxNzE4OX0.bOb5pEvUAtoGTvYcMNyaEhEzMSNGq7ETxuO-SIuKQ1FM7Xv-xqBr5pj4977yTW6qptVLIb_wyo95IVr1CZkCfQ
     * user : {"email":"200","gender":0,"id":1,"isDelete":0,"lastLoginIp":"192.168.1.138","lastLoginTime":1559377189054,"mobile":"18818888888","password":"03cf40e1f97e94a3b017e8554fee6159","realName":"ceshi","userIcon":"default_icon.png","userType":0,"username":"admin"}
     */

    private String token;
    /**
     * email : 200
     * gender : 0
     * id : 1
     * isDelete : 0
     * lastLoginIp : 192.168.1.138
     * lastLoginTime : 1559377189054
     * mobile : 18818888888
     * password : 03cf40e1f97e94a3b017e8554fee6159
     * realName : ceshi
     * userIcon : default_icon.png
     * userType : 0
     * username : admin
     */

    private UserBean user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private String email;
        private int gender;
        private int id;
        private int isDelete;
        private String lastLoginIp;
        private long lastLoginTime;
        private String mobile;
        private String password;
        private String realName;
        private String userIcon;
        private int userType;
        private String username;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getLastLoginIp() {
            return lastLoginIp;
        }

        public void setLastLoginIp(String lastLoginIp) {
            this.lastLoginIp = lastLoginIp;
        }

        public long getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(long lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
