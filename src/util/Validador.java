package util;

public class Validador {

    public static boolean validarTexto(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarIdade(int idade) {
        return idade > 0 && idade <= 120;
    }

    public static boolean validarNivelDor(int nivelDor) {
        return nivelDor >= 0 && nivelDor <= 10;
    }
}
