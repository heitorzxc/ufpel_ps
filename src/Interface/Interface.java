package src.Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import src.Montador.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

public class Controller {

    @FXML
    private File selectedFile;

    @FXML
    private Boolean flagD = true;

    @FXML
    private MenuButton buttonChoiceRun;

    @FXML
    private RadioButton buttonShowDetails;

    @FXML
    private TextField registerA;

    @FXML
    private TextField registerB;

    @FXML
    private TextField registerL;

    @FXML
    private TextField registerPC;

    @FXML
    private TextField registerS;

    @FXML
    private TextField registerT;

    @FXML
    private TextField registerW;

    @FXML
    private TextField registerX;

    @FXML
    private TextField textOutput;

    @FXML
    private TextField textNumInst;

    @FXML
    private Maquina maquina;

    @FXML
    private String textoPrompt;

    @FXML
    private Montador montador;

    @FXML
    private TextField textInstrucao;

    @FXML
    private TableView<DadosMemoria> tableView;

    @FXML
    private TableColumn<DadosMemoria, String> colunaEndereco;

    @FXML
    private TableColumn<DadosMemoria, String> colunaNumBin;

    @FXML
    private TableColumn<DadosMemoria, String> colunaInsHexa;

    @FXML
    private TableColumn<DadosMemoria, String> colunaOpcode;

    @FXML
    private TableColumn<DadosMemoria, String> colunaEnderecoBinario;

    @FXML
    private TableColumn<DadosMemoria, String> colunaNixbpq;

    @FXML
    private int step = 0;

    @FXML
    private String loadTo = "Default";

    @FXML
    private boolean stepMode;

    @FXML
    private ImageView LOADimg;

    @FXML
    private ImageView RUNimg;

    @FXML
    private ImageView STEPimg;

    // Caixa onde digitamos ou sobrescrevemos códigos!!
    @FXML
    private TextArea textCODE;

    // Caixa onde representamos nosso terminal!!
    @FXML
    private TextArea textTERMINAL;

    DropShadow dropShadow = new DropShadow();

    // ÍCONE LOAD - CLIQUE
    @FXML
    void LOADimgClick(MouseEvent event) throws FileNotFoundException {
        // Carrega o arquivo para o montador
        // Ou para o simulador?
        // Ou para ambos?
    }

    // ÍCONE MOUNT - CLIQUE
    @FXML
    void MOUNTimgClick(MouseEvent event) {
        // Invoca o montador e imprime no
    }

    // ÍCONE STEP - CLIQUE
    @FXML
    void STEPimgclick(MouseEvent event) throws IOException {
    }

    // ÍCONE RUN - CLIQUE
    @FXML
    void RUNimgclick(MouseEvent event) throws IOException {
    }

    // ---------- SOMBREAMENTO DOS ÍCONES ---------- //
    @FXML
    void LOADimgEntered(MouseEvent event) {
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.5));
        LOADimg.setEffect(dropShadow);
    }

    @FXML
    void LOADimgExit(MouseEvent event) {
        LOADimg.setEffect(null);
    }

    @FXML
    void MOUNTimgEntered(MouseEvent event) {
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.5));
        MOUNTimg.setEffect(dropShadow);
    }

    @FXML
    void MOUNTimgExit(MouseEvent event) {
        MOUNTimg.setEffect(null);
    }

    @FXML
    void STEPimgEntered(MouseEvent event) {
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.5));
        STEPimg.setEffect(dropShadow);
    }

    @FXML
    void STEPimgExit(MouseEvent event) {
        STEPimg.setEffect(null);
    }

    @FXML
    void RUNimgEntered(MouseEvent event) {
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.5));
        RUNimg.setEffect(dropShadow);
    }

    @FXML
    void RUNimgExit(MouseEvent event) {
        RUNimg.setEffect(null);
    }
    // ---------- SOMBREAMENTO DOS ÍCONES ---------- //

    // Criação e exibição da tabela que representa a memória
    @FXML
    public void handleTABLE(ObservableList<DadosMemoria> dados, int linhaAtual) {
    }

    // Métodos auxiliares

    private void exibirMensagemErro(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    private void executarMontador(boolean stepMode) throws FileNotFoundException {
        montador.leArquivo(selectedFile.getAbsolutePath());
        handleTERMINAL("Arquivo Carregado no Montador");
        montador.executarMontador("./resources/resultados/saida.txt", flagD);
        maquina.carregarInstrucoes("./resources/resultados/saida.txt");

    }

    private void executarMaquina(boolean stepMode) {
        maquina.carregarInstrucoes(selectedFile.getAbsolutePath());
        handleTERMINAL("Arquivo Carregado na Máquina");
        if (stepMode) {
            maquina.executarProgramaStep(step);
            step += 1;
        } else {
            maquina.executarPrograma();
        }
    }

    private void executarFluxoCompleto(boolean stepMode) throws IOException {
        handleTERMINAL("Executando Fluxo Completo");
    }

    // Saidas Gerais de texto
    @FXML
    public void handleTextInstrucao(String text) {
        textInstrucao.setText(text);
    }

    // Aqui é um método para imprimir no terminal!!
    @FXML
    public void handleTERMINAL(String dado) {
        textTERMINAL.appendText(dado + "\n");
    }

    // Aqui é um método para imprimir na célula de código!!
    @FXML
    public void handleCODE(String code) {
        textCODE.appendText(code + "\n");
    }

    @FXML
    public void handleNumInst(String NumInst) {
        textNumInst.setText(NumInst);
    }

    @FXML
    public void handleOutput(String output) {
        textOutput.setText(output);
    }

    // Métodos para setar os textos das caixas de texto
    @FXML
    void texto_Reg_A(String dadoA) {
        registerA.setText(dadoA);
    }

    @FXML
    public void handleregAa(String dadoA) {
        registerA.setText(dadoA);
    }

    @FXML
    void handleregB(String dadoB) {
        registerB.setText(dadoB);
    }

    @FXML
    public void handleregBb(String dadoB) {
        registerB.setText(dadoB);
    }

    @FXML
    void handleregL(String dadoL) {
        registerL.setText(dadoL);
    }

    @FXML
    public void handleregLl(String dadoL) {
        registerL.setText(dadoL);
    }

    @FXML
    void handleregPC(String dadoPC) {
        registerPC.setText(dadoPC);
    }

    @FXML
    public void handleregPCpc(String dadoPC) {
        registerPC.setText(dadoPC);
    }

    @FXML
    void handleregS(String dadoS) {
        registerS.setText(dadoS);
    }

    @FXML
    public void handleregSs(String dadoS) {
        registerS.setText(dadoS);
    }

    @FXML
    void handleregT(String dadoT) {
        registerT.setText(dadoT);
    }

    @FXML
    public void handleregTt(String dadoT) {
        registerT.setText(dadoT);
    }

    @FXML
    void handleregW(String dadoW) {
        registerW.setText(dadoW);
    }

    @FXML
    public void handleregWw(String dadoW) {
        registerW.setText(dadoW);
    }

    @FXML
    void handleregX(String dadoX) {
        registerX.setText(dadoX);
    }

    @FXML
    public void handleregXx(String dadoX) {
        registerX.setText(dadoX);
    }

}
