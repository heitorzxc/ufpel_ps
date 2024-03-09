package src.Interface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import src.Montador.Montador;
import src.Registradores.BancoRegistradores;
import src.Exceptions.RegisterIdenfierError;
import src.Maquina.Maquina;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

public class Controller {

    // Gerais
    @FXML
    private File selectedFile;

    @FXML
    private Memoria memoria;

    @FXML
    private String textoPrompt;

    @FXML
    private Button RUNBUTTON;

    @FXML
    private Button STEPBUTTON;

    // Exibição dos registradores
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

    // Tabela de represenação da memória
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
    private TableColumn<Endereco, String> colunaNixbpe;

    // Botões LOAD, RUN, STEP, MOUNT...
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
        // Invoca o montador e imprime no ?
    }

    // ÍCONE STEP - CLIQUE
    @FXML
    void STEPimgclick(MouseEvent event) throws IOException {
    }

    // ÍCONE RUN - CLIQUE
    @FXML
    void RUNimgclick(MouseEvent event) {
        try {

            Maquina.getInstance().executarPrograma();
        } catch (Exception e) {
            exibirMensagemErro("Erro de execução", "", "Insira um código assembly ou selecione um arquivo!");
        }
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

    // Atualiza Registradores
    public void atualizarRegistradores() {
        try {
            BancoRegistradores regs = BancoRegistradores.getInstance();
            handleTERMINAL("Atualizando Registradores");

            registerA.setText(String.valueOf(regs.getValor("A")));
            registerL.setText(String.valueOf(regs.getValor("L")));
            registerB.setText(String.valueOf(regs.getValor("B")));
            registerPC.setText(String.valueOf(regs.getValor("PC")));
            registerS.setText(String.valueOf(regs.getValor("S")));
            registerT.setText(String.valueOf(regs.getValor("T")));
            registerW.setText(String.valueOf(regs.getValor("SW")));
            registerX.setText(String.valueOf(regs.getValor("X")));

            handleTERMINAL("Registradores Atualizados");
        } catch (RegisterIdenfierError e) {
            exibirMensagemErro("Rigistrador não encontrado", "", "registrador não foi encontrado. Revise o codigo");
        }

    }

    // Criação e exibição da tabela que representa a memória

    @FXML
    public void handleTABLE() {
        // colunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaNumBin.setCellValueFactory(new PropertyValueFactory<>("InstrucaoBinario"));
        colunaInsHexa.setCellValueFactory(new PropertyValueFactory<>("InstrucaoHexa"));
        colunaOpcode.setCellValueFactory(new PropertyValueFactory<>("opcode"));
        colunaEnderecoBinario.setCellValueFactory(new PropertyValueFactory<>("Endereco"));
        colunaNixbpe.setCellValueFactory(new PropertyValueFactory<>("NIXBPE"));
        // tableView.setItems(Memoria.getInstance().getMemoria());
        // Cria um ObservableList vinculado à ObservableList da classe Memoria
        // ObservableList<Endereco> observableList =
        // FXCollections.observableArrayList(Memoria.getInstance().getMemoria());

        // Adicione o Listener à lista
        // Memoria.getInstance().setListener((ListChangeListener<Endereco>) change -> {
        // while (change.next()) {
        // // if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
        // tableView.refresh(); // update tabela da memoria;
        // // tableView.setItems(Memoria.getInstance().getMemoria());
        // // }
        // }
        // });

        // Configura a TableView com a ObservableList

    }

    // Atualização da interface
    @FXML
    public void updateInterface() {
        ChangeListener<Number> listener = (observable, oldValue, newValue) -> {
            atualizarRegistradores();
        };
        BancoRegistradores.getInstance().setListener(listener);
    }

    // TESTE RUN
    @FXML
    void testeRUN(ActionEvent event) throws Exception {
        Maquina.getInstance().executarPrograma();
    }

    // TESTE STEP
    @FXML
    void testeSTEP(ActionEvent event) throws Exception {
        Maquina.getInstance().step();
    }

    // Exibir mensagem de erro
    private void exibirMensagemErro(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    // Imprimir no terminal
    @FXML
    public void handleTERMINAL(String dado) {
        textTERMINAL.appendText(dado + "\n");
    }

    // Imprimir na caixa de código
    @FXML
    public void handleCODE(String code) {
        textCODE.appendText(code + "\n");
    }
}
