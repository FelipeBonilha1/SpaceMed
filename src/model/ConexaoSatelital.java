package model;

import interfaces.Conectavel;

public class ConexaoSatelital implements Conectavel {

    private String nomeSatelite;
    private double velocidadeMbps;
    private boolean ativa;

    public ConexaoSatelital(String nomeSatelite, double velocidadeMbps) {
        this.nomeSatelite = nomeSatelite;
        this.velocidadeMbps = velocidadeMbps;
        this.ativa = false;
    }

    @Override
    public void conectar() {
        this.ativa = true;
        System.out.println("  [SATÉLITE] Conectado ao satélite: " + nomeSatelite + " (" + velocidadeMbps + " Mbps)");
    }

    @Override
    public void desconectar() {
        this.ativa = false;
        System.out.println("  [SATÉLITE] Desconectado do satélite: " + nomeSatelite);
    }

    @Override
    public boolean testarConexao() {
        if (ativa) {
            System.out.println("  [SATÉLITE] Conexão com " + nomeSatelite + " está ATIVA (" + velocidadeMbps + " Mbps)");
        } else {
            System.out.println("  [SATÉLITE] Conexão com " + nomeSatelite + " está INATIVA");
        }
        return ativa;
    }

    public void exibirStatus() {
        System.out.println("  Satélite  : " + nomeSatelite);
        System.out.println("  Velocidade: " + velocidadeMbps + " Mbps");
        System.out.println("  Status    : " + (ativa ? "ATIVA" : "INATIVA"));
    }

    public String getNomeSatelite() {
        return nomeSatelite;
    }

    public void setNomeSatelite(String nomeSatelite) {
        this.nomeSatelite = nomeSatelite;
    }

    public double getVelocidadeMbps() {
        return velocidadeMbps;
    }

    public void setVelocidadeMbps(double velocidadeMbps) {
        this.velocidadeMbps = velocidadeMbps;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
