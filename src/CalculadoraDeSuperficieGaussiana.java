import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraDeSuperficieGaussiana extends JFrame {
    private static final double PERMISSIVIDADE_VACUO = 8.85e-12;
    private JTextField campoDimensaoCubo;
    private JLabel rotuloResultado;

    public CalculadoraDeSuperficieGaussiana() {
        setTitle("Calculadora de Carga Total no Cubo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Dimensão do Cubo (m):"));
        campoDimensaoCubo = new JTextField(10);
        add(campoDimensaoCubo);

        JButton botaoCalcular = new JButton("Calcular Carga");
        botaoCalcular.addActionListener(new OuvinteBotaoCalcular());
        add(botaoCalcular);

        rotuloResultado = new JLabel("Carga Total: ");
        add(rotuloResultado);
    }

    private class OuvinteBotaoCalcular implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double dimensaoCubo = Double.parseDouble(campoDimensaoCubo.getText());

                double areaFace = Math.pow(dimensaoCubo, 2);

                double xMais = dimensaoCubo / 2.0;
                double xMenos = -dimensaoCubo / 2.0;

                double campoMais = 3 * xMais + 4; 
                double campoMenos = 3 * xMenos + 4; 

                double fluxoX = (campoMais - campoMenos) * areaFace;

                double cargaTotal = PERMISSIVIDADE_VACUO * fluxoX;

                rotuloResultado.setText(String.format("Carga Total: %.2e C", cargaTotal));
            } catch (NumberFormatException ex) {
                rotuloResultado.setText("Erro: Insira uma dimensão válida.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraDeSuperficieGaussiana calculadora = new CalculadoraDeSuperficieGaussiana();
            calculadora.setVisible(true);
        });
    }
}

