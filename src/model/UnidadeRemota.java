package model;

public class UnidadeRemota {

    private int id;
    private String nome;
    private String localizacao;
    private String tipoComunidade;
    private ConexaoSatelital conexao;

    public UnidadeRemota(int id, String nome, String localizacao, String tipoComunidade, ConexaoSatelital conexao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.tipoComunidade = tipoComunidade;
        this.conexao = conexao;
    }

    public void verificarConexao() {
        System.out.println("  Verificando conexão da unidade: " + nome);
        conexao.testarConexao();
    }

    public void exibirUnidade() {
        System.out.println("==============================");
        System.out.println("  UNIDADE REMOTA");
        System.out.println("==============================");
        System.out.println("  ID             : " + id);
        System.out.println("  Nome           : " + nome);
        System.out.println("  Localização    : " + localizacao);
        System.out.println("  Tipo Comunidade: " + tipoComunidade);
        System.out.println("  --- Conexão Satelital ---");
        conexao.exibirStatus();
        System.out.println("==============================");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipoComunidade() {
        return tipoComunidade;
    }

    public void setTipoComunidade(String tipoComunidade) {
        this.tipoComunidade = tipoComunidade;
    }

    public ConexaoSatelital getConexao() {
        return conexao;
    }

    public void setConexao(ConexaoSatelital conexao) {
        this.conexao = conexao;
    }
}
