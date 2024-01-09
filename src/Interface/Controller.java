package src.Interface;

import java.io.File;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import src.Maquina.Maquina;

public class Controller {
    
    
    @FXML
    private Button buttonLOADPROMPT;

    @FXML
    private Button buttonRUN;

    @FXML
    private Button buttonSELECTFILE;

    @FXML
    private Button buttonSTEP;

    @FXML
    private Button buttonImprimirMemoria;

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
    private Slider sliderSPEED;

    @FXML
    private TextField textBinInstrucao;

    @FXML
    private TextArea textCODE;

    @FXML
    private TextField textEndBin;

    @FXML
    private TextField textEndInstrucao;

    @FXML
    private TextField textHexInst;

    @FXML
    private TextField textNIXBPE;

    @FXML
    private TextField textOPCODE;

    @FXML
    private TextField textOutput;

    @FXML
    private TextArea textTERMINAL;
    
    @FXML
    private TextField textNumInst;
    
    @FXML
    private Maquina maquina;

    @FXML
    private String textoPrompt;

   

    @FXML
    private int step=0;

    public Controller(){
    }
    public void setMaquina(Maquina A){
        maquina = A;
    }

    @FXML
    public void handleNumInst(String NumInst){
        textNumInst.setText(NumInst);
    }
    @FXML
    void handleBinInstrucao(String BinInstrucao) {
        textBinInstrucao.setText(BinInstrucao);
    }

    @FXML
    void handleEndBin(String EndBin) {
        textEndBin.setText(EndBin);
    }

    @FXML
    void handleEndInstrucao(String EndInstrucao) {
        textEndInstrucao.setText(EndInstrucao);
    }

    @FXML
    void handleHexInst(String HexInst) {
        textHexInst.setText(HexInst);
    }

    @FXML
    void handleNIXBPE(String NIXBPE) {
        textNIXBPE.setText(NIXBPE);
    }

    @FXML
    void handleOPCODE(String opcode) {
        textOPCODE.setText(opcode);
    }

    @FXML
    void handleOutput(String output) {
        textOutput.setText(output);
    }

    @FXML
    public void handleTERMINAL(String dado) {
        textTERMINAL.appendText(dado + "\n");
    }

    @FXML
    void handlebuttonLOADPROMPT(ActionEvent event) {
        textoPrompt = textCODE.getText();
        maquina.carregarInstrucoesDoTextField(textoPrompt);
    }

    @FXML
    void handlebuttonRUN(ActionEvent event) {
        maquina.executarPrograma();
    }
    @FXML
    void handleImprimirMemoria(ActionEvent event){
        maquina.imprimirMemoria();
    }

    @FXML
 public void handlebuttonSELECTFILE(ActionEvent event) {
         // Criar um FileChooser
        FileChooser fileChooser = new FileChooser();
        
        // Configurar título (opcional)
        fileChooser.setTitle("Escolha um Arquivo");

        // Exibir a janela de seleção de arquivo
        Stage stage = (Stage) buttonSELECTFILE.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Processar o arquivo selecionado (você pode ajustar conforme necessário)
        if (selectedFile != null) {
            handleTERMINAL("Arquivo selecionado: " + selectedFile.getName());
            // Faça algo com o arquivo selecionado, como atualizar um campo de texto, etc.
        } else {
            handleTERMINAL("Nenhum arquivo selecionado.");
        }
        maquina.carregarInstrucoes(selectedFile.getAbsolutePath());
  
    }

    @FXML
    void handlebuttonSTEP(ActionEvent event) {
        maquina.executarProgramaStep(step);
        step+=1;
    }

    @FXML
    void handleregA(String dadoA) {
        registerA.setText(dadoA);
    }

    @FXML
    void handleregB(String dadoB) {
        registerB.setText(dadoB);
    }

    @FXML
    void handleregL(String dadoL) {
        registerL.setText(dadoL);
    }

    @FXML
    void handleregPC(String dadoPC) {
        registerPC.setText(dadoPC);
    }

    @FXML
    void handleregS(String dadoS) {
        registerS.setText(dadoS);
    }

    @FXML
    void handleregT(String dadoT) {
        registerT.setText(dadoT);
    }

    @FXML
    void handleregW(String dadoW) {
        registerW.setText(dadoW);
    }

    @FXML
    void handleregX(String dadoX) {
        registerX.setText(dadoX);
    }

    @FXML
    void handlesliderSPEED(MouseEvent event) {

    }

}