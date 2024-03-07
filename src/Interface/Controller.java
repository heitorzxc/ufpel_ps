package src.Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import src.Memoria.Endereco;
import src.Memoria.Memoria;
import src.Montador.*;
import src.Registradores.Registrador;
import src.Exceptions.RegisterIdenfierError;
import src.Maquina.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

public class Controller {

    //Gerais
    @FXML
    private File selectedFile;

    @FXML
    private Maquina maquina;

    @FXML
    private Memoria memoria;

    @FXML
    private String textoPrompt;
    
    @FXML
    private Button RUNBUTTON;

    @FXML
    private Button STEPBUTTON;
    //TextFields
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
    private TextField textInstrucao;

    //TableView
    @FXML
    private TableView<Endereco> tableView;

    @FXML
    private TableColumn<Endereco, String> colunaEndereco;

    @FXML
    private TableColumn<Endereco, String> colunaNumBin;

    @FXML
    private TableColumn<Endereco, String> colunaInsHexa;

    @FXML
    private TableColumn<Endereco, String> colunaOpcode;

    @FXML
    private TableColumn<Endereco, String> colunaEnderecoBinario;

    @FXML
    private TableColumn<Endereco, String> colunaNixbpq;

    //ImageView
    @FXML
    private ImageView LOADimg;

    @FXML
    private ImageView RUNimg;

    @FXML
    private ImageView STEPimg;

    @FXML
    private ImageView MOUNTimg;

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
    void RUNimgclick(MouseEvent event) throws Exception {
        maquina = new Maquina("./resources/binarios/exemplo.txt");
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

    public void setMaquina(Maquina maquina){
        this.maquina = maquina;
    }
    //Atualiza Registradores
    public void atualizarRegistradores() throws RegisterIdenfierError {
        handleTERMINAL("Atualizando Registradores");
        registerA.setText(String.valueOf(maquina.registradores.getValor("A")));
        registerB.setText(String.valueOf(maquina.registradores.getValor("B")));
        registerL.setText(String.valueOf(maquina.registradores.getValor("L")));
        registerPC.setText(String.valueOf(maquina.registradores.getValor("PC")));
        registerS.setText(String.valueOf(maquina.registradores.getValor("S")));
        registerT.setText(String.valueOf(maquina.registradores.getValor("T")));
        registerW.setText(String.valueOf(maquina.registradores.getValor("SW")));
        registerX.setText(String.valueOf(maquina.registradores.getValor("X")));
        handleTERMINAL("Registradores Atualizados");
    }
    

    // Criação e exibição da tabela que representa a memória
  @FXML
public void handleTABLE() {
    //colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    colunaNumBin.setCellValueFactory(new PropertyValueFactory<>("instrucaoBinario"));
    colunaInsHexa.setCellValueFactory(new PropertyValueFactory<>("insHexa"));
    colunaOpcode.setCellValueFactory(new PropertyValueFactory<>("opcode"));
    colunaEnderecoBinario.setCellValueFactory(new PropertyValueFactory<>("enderecoBinario"));
    colunaNixbpq.setCellValueFactory(new PropertyValueFactory<>("nixbpe"));
    

    // Cria um ObservableList vinculado à ObservableList da classe Memoria
    ObservableList<Endereco> observableList = FXCollections.observableArrayList(maquina.memoria.getMemoria());

    // Adicione o Listener à lista
    maquina.memoria.setListener((ListChangeListener<Endereco>) change -> {
        while (change.next()) {
            if (change.wasAdded()) {
                // Adicione os itens adicionados à ObservableList
                observableList.addAll(change.getAddedSubList());
            }
            if (change.wasRemoved()) {
                // Remova os itens removidos da ObservableList
                observableList.removeAll(change.getRemoved());
            }
        }
    });

    // Configura a TableView com a ObservableList
    tableView.setItems(observableList);
    tableView.refresh();
}
    @FXML

    public void updateInterface(){
            maquina.registradores.setListener((MapChangeListener<String, Registrador>) change  -> {
            if(change.wasAdded() || change.wasRemoved()){
                handleTERMINAL("Alteração nos registradores detectada. Chamando atualizarRegistradores...");
            
            try {
                atualizarRegistradores();
            } catch (RegisterIdenfierError e) {
                e.printStackTrace();
            }
        }
        });
        handleTERMINAL("UpdateInterface");
    }


    //METODOS DE TEXTE
    @FXML
    void testeRUN(ActionEvent event) throws Exception{
        maquina.executarPrograma();
        atualizarRegistradores();
        updateInterface();
        handleTABLE();
        
        

    }
    @FXML
    void testeSTEP(ActionEvent event){

    }

    // Métodos auxiliares

    private void exibirMensagemErro(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
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
