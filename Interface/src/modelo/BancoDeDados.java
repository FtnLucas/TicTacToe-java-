package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BancoDeDados {

    public static void gerarArquivo(ArrayList<Jogador> jogadores) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("Jogadores", List.class);
        xstream.alias("Jogador", Jogador.class);
        File arquivo = new File("jogadores.xml");
        FileOutputStream gravar;
        try {
            gravar = new FileOutputStream(arquivo);
            gravar.write(xstream.toXML(jogadores).getBytes());
            gravar.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Jogador> lerArquivo() {
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("Jogadores", List.class);
            xstream.alias("Jogador", Jogador.class);
            xstream.processAnnotations(Jogador.class);
            BufferedReader input = new BufferedReader(new FileReader("jogadores.xml"));
            ArrayList<Jogador> jogadores = (ArrayList<Jogador>) xstream.fromXML(input);
            input.close();

            // print
			/*
             * for (Jogador jogador : jogadores) { System.out.println("Nome de usuário: " +
             * jogador.getLogin()); System.out.println("Senha: " + jogador.getSenha());
             * System.out.println("E-mail: " + jogador.getEmail());
             * System.out.println("Pontuação: " + jogador.getPontos() + " ponto(s)");
             * System.out.println("Data de cadastro: " + jogador.getDataCadastro());
             * System.out.println("\n-----------------------------------------\n"); }
             */
            return jogadores;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
