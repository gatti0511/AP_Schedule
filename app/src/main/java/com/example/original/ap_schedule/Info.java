package com.example.original.ap_schedule;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Info extends RealmObject {
    @PrimaryKey
    private String name;

    private boolean flg_security;
    private boolean flg_architecture;
    private boolean flg_program;
    private boolean flg_network;
    private boolean flg_database;
    private boolean flg_audit;

    public Info() { }

    public Info(String name, boolean flg_security, boolean flg_architecture, boolean flg_program, boolean flg_network, boolean flg_database, boolean flg_audit) {
        this.name = name;
        this.flg_security = flg_security;
        this.flg_architecture = flg_architecture;
        this.flg_program = flg_program;
        this.flg_network = flg_network;
        this.flg_database = flg_database;
        this.flg_audit = flg_audit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlg_security() {
        return flg_security;
    }

    public void setFlg_security(boolean flg_security) {
        this.flg_security = flg_security;
    }

    public boolean isFlg_architecture() {
        return flg_architecture;
    }

    public void setFlg_architecture(boolean flg_architecture) {
        this.flg_architecture = flg_architecture;
    }

    public boolean isFlg_program() {
        return flg_program;
    }

    public void setFlg_program(boolean flg_program) {
        this.flg_program = flg_program;
    }

    public boolean isFlg_network() {
        return flg_network;
    }

    public void setFlg_network(boolean flg_network) {
        this.flg_network = flg_network;
    }

    public boolean isFlg_database() {
        return flg_database;
    }

    public void setFlg_database(boolean flg_database) {
        this.flg_database = flg_database;
    }

    public boolean isFlg_audit() {
        return flg_audit;
    }

    public void setFlg_audit(boolean flg_audit) {
        this.flg_audit = flg_audit;
    }
}
