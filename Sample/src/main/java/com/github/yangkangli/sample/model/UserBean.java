package com.github.yangkangli.sample.model;

public class UserBean {

    /**
     * id : 1
     * firstname : Amit
     * lastname : Shekhar
     */

    private String id;
    private String firstname;
    private String lastname;

    private boolean formLocal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isFormLocal() {
        return formLocal;
    }

    public void setFormLocal(boolean formLocal) {
        this.formLocal = formLocal;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", formLocal=" + formLocal +
                '}';
    }
}
