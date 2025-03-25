package org.intuitivecare;

public class Main {
    public static void main(String[] args) {
        AtualizaRolProcedimentos robo = new AtualizaRolProcedimentos();

        if(robo.configuraRobo()){
            robo.EntraNoSite();
        }
    }
}
