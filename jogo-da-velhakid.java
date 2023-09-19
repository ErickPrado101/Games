import java.util.Scanner;
import java.util.Random;

public class JogoDaVelha {
    public static void main(String[] args) {
        char[][] tabuleiro = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        boolean jogoEmAndamento = true;
        char jogadorAtual = 'X';

        Scanner scanner = new Scanner(System.in);

        while (jogoEmAndamento) {
            exibirTabuleiro(tabuleiro);
            if (jogadorAtual == 'X') {
                realizarJogadaHumana(tabuleiro, scanner);
            } else {
                realizarJogadaAI(tabuleiro);
            }

            if (verificarVitoria(tabuleiro, jogadorAtual)) {
                exibirTabuleiro(tabuleiro);
                System.out.println("Jogador " + jogadorAtual + " venceu!");
                jogoEmAndamento = false;
            } else if (tabuleiroCheio(tabuleiro)) {
                exibirTabuleiro(tabuleiro);
                System.out.println("Empate!");
                jogoEmAndamento = false;
            }

            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    public static void exibirTabuleiro(char[][] tabuleiro) {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----");
            }
        }
        System.out.println();
    }

    public static void realizarJogadaHumana(char[][] tabuleiro, Scanner scanner) {
        int linha, coluna;
        do {
            System.out.print("Informe a linha (1, 2 ou 3) e a coluna (1, 2 ou 3) para sua jogada: ");
            linha = scanner.nextInt() - 1;
            coluna = scanner.nextInt() - 1;
        } while (!jogadaValida(tabuleiro, linha, coluna));

        tabuleiro[linha][coluna] = 'X';
    }

    public static void realizarJogadaAI(char[][] tabuleiro) {
        Random rand = new Random();
        int linha, coluna;
        do {
            linha = rand.nextInt(3);
            coluna = rand.nextInt(3);
        } while (!jogadaValida(tabuleiro, linha, coluna));

        tabuleiro[linha][coluna] = 'O';
    }

    public static boolean jogadaValida(char[][] tabuleiro, int linha, int coluna) {
        if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ') {
            return true;
        }
        return false;
    }

    public static boolean verificarVitoria(char[][] tabuleiro, char jogador) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true; // Linhas
            }
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true; // Colunas
            }
        }
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true; // Diagonal principal
        }
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true; // Diagonal secundária
        }
        return false;
    }

    public static boolean tabuleiroCheio(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
