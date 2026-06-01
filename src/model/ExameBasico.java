package model;

public class ExameBasico {

    private int id;
    private Paciente paciente;
    private String tipoExame;
    private String resultado;
    private String data;

    public ExameBasico(int id, Paciente paciente, String tipoExame, String resultado, String data) {
        this.id = id;
        this.paciente = paciente;
        this.tipoExame = tipoExame;
        this.resultado = resultado;
        this.data = data;
    }

    public void exibirExame() {
        System.out.println("==============================");
        System.out.println("  EXAME BÁSICO");
        System.out.println("==============================");
        System.out.println("  ID        : " + id);
        System.out.println("  Paciente  : " + paciente.getNome());
        System.out.println("  Tipo Exame: " + tipoExame);
        System.out.println("  Resultado : " + resultado);
        System.out.println("  Data      : " + data);
        System.out.println("==============================");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
