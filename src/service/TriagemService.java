package service;

public class TriagemService {

    public String calcularPrioridade(boolean faltaAr, boolean febre, int nivelDor) {
        if (faltaAr || nivelDor >= 9) {
            return "EMERGÊNCIA";
        } else if (nivelDor >= 7 || febre) {
            return "ALTA";
        } else if (nivelDor >= 4) {
            return "MÉDIA";
        } else {
            return "BAIXA";
        }
    }
}
