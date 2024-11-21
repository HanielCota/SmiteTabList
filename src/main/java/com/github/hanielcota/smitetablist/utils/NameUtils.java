package com.github.hanielcota.smitetablist.utils;

/**
 * A classe {@code NameUtils} fornece métodos utilitários para manipulação de nomes de jogadores.
 *
 * <p>Inclui funções para criar nomes personalizados no tablist, respeitando o limite de caracteres
 * imposto pelo Minecraft (16 caracteres).
 */
public class NameUtils {

    /**
     * O número máximo de caracteres permitidos para o nome no tablist.
     */
    private static final int MAX_NAME_LENGTH = 16;

    /**
     * Cria um nome para exibição no tablist com base em um prefixo e no nome do jogador.
     *
     * <p>Se o comprimento total do nome gerado (prefixo + nome do jogador) exceder o limite
     * de {@code MAX_NAME_LENGTH}, ele será truncado para o número máximo de caracteres.
     *
     * @param prefix O prefixo a ser adicionado antes do nome do jogador (por exemplo, "[Admin] ").
     * @param playerName O nome do jogador.
     * @return O nome formatado para exibição no tablist, truncado se necessário.
     */
    public static String createTabName(String prefix, String playerName) {
        String tabName = prefix + playerName;
        return tabName.length() > MAX_NAME_LENGTH ? tabName.substring(0, MAX_NAME_LENGTH) : tabName;
    }
}
