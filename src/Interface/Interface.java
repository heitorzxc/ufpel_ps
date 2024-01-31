package src.Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {
    private JTextField caixa_Texto_A;
    private JTextField caixa_Texto_X;
    private JTextField caixa_Texto_L;
    private JTextField caixa_Texto_B;
    private JTextField caixa_Texto_S;
    private JTextField caixa_Texto_T;
    private JTextField caixa_Texto_F;
    private JTextField caixa_Texto_PC;
    private JTextField caixa_Texto_SW;

    public Interface() {
        super("Simulador SIC");

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        // Criação dos componentes
        JLabel legenda_Registrador_A = new JLabel("Registrador A:");
        caixa_Texto_A = new JTextField();
        caixa_Texto_A.setEditable(false);

        JLabel legenda_Registrador_X = new JLabel("Registrador X:");
        caixa_Texto_X = new JTextField();
        caixa_Texto_X.setEditable(false);

        JLabel legenda_Registrador_L = new JLabel("Registrador L:");
        caixa_Texto_L = new JTextField();
        caixa_Texto_L.setEditable(false);

        JLabel legenda_Registrador_B = new JLabel("Registrador B:");
        caixa_Texto_B = new JTextField();
        caixa_Texto_B.setEditable(false);

        JLabel legenda_Registrador_S = new JLabel("Registrador S:");
        caixa_Texto_S = new JTextField();
        caixa_Texto_S.setEditable(false);

        JLabel legenda_Registrador_T = new JLabel("Registrador T:");
        caixa_Texto_T = new JTextField();
        caixa_Texto_T.setEditable(false);

        JLabel legenda_Registrador_F = new JLabel("Registrador F:");
        caixa_Texto_F = new JTextField();
        caixa_Texto_F.setEditable(false);

        JLabel legenda_Registrador_PC = new JLabel("Registrador PC:");
        caixa_Texto_PC = new JTextField();
        caixa_Texto_PC.setEditable(false);

        JLabel legenda_Registrador_SW = new JLabel("Registrador SW:");
        caixa_Texto_SW = new JTextField();
        caixa_Texto_SW.setEditable(false);

        // Botões da interface
        JButton botaoPasso = new JButton("Step");
        JButton botaoExecutar = new JButton("Run");
        JSlider deslizadorVelocidade = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

        // Adiciona os componentes à janela
        add(legenda_Registrador_A);
        add(caixa_Texto_A);
        add(legenda_Registrador_X);
        add(caixa_Texto_X);
        add(legenda_Registrador_L);
        add(caixa_Texto_L);
        add(legenda_Registrador_B);
        add(caixa_Texto_B);
        add(legenda_Registrador_S);
        add(caixa_Texto_S);
        add(legenda_Registrador_T);
        add(caixa_Texto_T);
        add(legenda_Registrador_F);
        add(caixa_Texto_F);
        add(legenda_Registrador_PC);
        add(caixa_Texto_PC);
        add(legenda_Registrador_SW);
        add(caixa_Texto_SW);
        add(botaoPasso);
        add(botaoExecutar);
        add(deslizadorVelocidade);

        // Configuração dos botões
        botaoPasso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica quando pressionado Step
            }
        });

        botaoExecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica quando pressionado Run
            }
        });

        // Exibe a janela
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface();
            }
        });
    }
}
