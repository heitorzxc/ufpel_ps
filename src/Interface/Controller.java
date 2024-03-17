package src.Interface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import src.Registradores.BancoRegistradores;
import src.Carregador.Carregador;
import src.Exceptions.RegisterIdenfierError;
import src.Instrucoes.Instrucoes;
import src.Ligador.Ligador;
import src.Maquina.Maquina;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

public class Controller {

    // Gerais
    @FXML
    private List<File> selectedFile;

    @FXML
    private Memoria memoria;

    @FXML
    private String[] paths;

    @FXML
    private boolean isAssembled;

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

    // Tabela de represenação da memória
    @FXML
    private TableView<Endereco> tableView;

    @FXML
    private TableColumn<Maquina, Number> colunaEndereco;

    @FXML
    private TableColumn<Endereco, String> colunaInsHexa;

    @FXML
    private TableColumn<Endereco, String> colunaOpcode;

    @FXML
    private TableColumn<Endereco, String> colunaNomeInstrucao;

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

    @FXML
    void LOADimgClick(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha um Arquivo");
      
    
        // Exibir a janela de seleção de arquivo
        Stage stage = (Stage) LOADimg.getScene().getWindow();
        selectedFile = fileChooser.showOpenMultipleDialog(stage);
    
        // Processar o arquivo selecionado
        if (selectedFile != null && !selectedFile.isEmpty()) {

            paths = new String[selectedFile.size()]; //Lista de caminhos
            for (int i = 0; i < selectedFile.size(); i++) {
                paths[i] = selectedFile.get(i).getPath();
            }

            for (File selectedFile : selectedFile) {
                textCODE.appendText("Arquivo selecionado: " + selectedFile.getName() + "\n");
                try {
                    byte[] fileBytes = Files.readAllBytes(selectedFile.toPath());
                          
                    // Le o conteúdo do arquivo e exibir no textCODE
                    String content = new String(fileBytes);
                    textCODE.appendText(content + "\n");
        
                } catch (IOException e) {
                    exibirMensagemErro("Erro de Leitura", "", "Ocorreu um erro na leitura do arquivo.");
                }
            }
        } else {
            handleTERMINAL("Nenhum arquivo selecionado.");
        }
    }
          

    // ÍCONE MOUNT - CLIQUE
    @FXML
    void MOUNTimgClick(MouseEvent event) {
        configurarExecução(); // aqui vai chamar o metodo que vai montar ou carregar diretamente na maquina se for binario
    }

    // ÍCONE STEP - CLIQUE
    @FXML
    void STEPimgclick(MouseEvent event)  {
        if(!isAssembled){
           configurarExecução();
        }else{ // se ja esta montado, executa a maquina
            try {
                Maquina.getInstance().step();
                tableView.refresh(); //atualiza a tabela para atualizar a cor (provisório)
            } catch (Exception e) {
            }
        }
    }

    // ÍCONE RUN - CLIQUE
    @FXML
    void RUNimgclick(MouseEvent event) {
        if(!isAssembled){
            configurarExecução();
        }else{ // se  já está montado, executa a maquina
            try {
                Maquina.getInstance().executarPrograma();
            } catch (Exception e) {
            }
        }
    }


    // Atualiza Registradores
    public void atualizarRegistradores() {
        try {
            BancoRegistradores regs = BancoRegistradores.getInstance();

            registerA.setText(String.valueOf(regs.getValor("A")));
            registerL.setText(String.valueOf(regs.getValor("L")));
            registerB.setText(String.valueOf(regs.getValor("B")));
            registerPC.setText(String.valueOf(regs.getValor("PC")));
            registerS.setText(String.valueOf(regs.getValor("S")));
            registerT.setText(String.valueOf(regs.getValor("T")));
            registerW.setText(String.valueOf(regs.getValor("SW")));
            registerX.setText(String.valueOf(regs.getValor("X")));

        } catch (RegisterIdenfierError e) {
            exibirMensagemErro("Registrador não encontrado", "", "Registrador não foi encontrado. Revise o código");
        }

    }

    // Criação e exibição da tabela que representa a memória
    @FXML
    public void handleTABLE() {
        
        colunaEndereco.setCellValueFactory(cellData -> {
            ObservableList<Endereco> memoria = Memoria.getInstance().getMemoria();
            int index = memoria.indexOf(cellData.getValue()); 
            return new ReadOnlyObjectWrapper<>(index); 
        });       
        colunaInsHexa.setCellValueFactory(new PropertyValueFactory<>("InstrucaoHexa"));
        colunaOpcode.setCellValueFactory(new PropertyValueFactory<>("opcode"));
        colunaNomeInstrucao.setCellValueFactory(new PropertyValueFactory<>("NomeInstrucao"));
        tableView.setItems(Memoria.getInstance().getMemoria());

        //aqui define a cor da linha
        tableView.setRowFactory(tv -> new TableRow<Endereco>() {
            @Override
            protected void updateItem(Endereco item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle(""); // Define o estilo padrão se o item estiver vazio
                } else {
                    if(registerPC.getText().equals("")){
                        registerPC.setText("0");
                    }
                    if (colunaEndereco.getCellData(getIndex()) == Integer.valueOf(registerPC.getText())) {
                        // Destaca a linha correspondente ao endereço atual
                        setStyle("-fx-background-color: yellow;"); // Altera a cor de fundo da linha
                    } else {
                        setStyle(""); // Define o estilo padrão se não estiver sendo executado
                    }
                }
            }
        });
        
    }

    // Atualização da interface
    @FXML
    public void setListeners() {
        handleTABLE();
        Memoria.getInstance().setListener((ListChangeListener<Endereco>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                    tableView.refresh(); // atualiza o tableView sempre que houver alteração na memoria
                }
            }
        });

        ChangeListener<Number> listener = (observable, oldValue, newValue) -> {
            atualizarRegistradores();
        };
        BancoRegistradores.getInstance().setListener(listener);
    }


    // Exibir mensagem de erro
    private void exibirMensagemErro(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    //Configurar execução
    public void configurarExecução(){

        try {

            Instrucoes.inicializaInstrucoes();

            Ligador ligador = new Ligador();
            ligador.executar(paths);
           
            Carregador carregador = new Carregador();
            carregador.executar("./resources/saidas/entrada_maquina.txt");
            
            isAssembled=true; // "status" da montagem
            handleTERMINAL("Arquivo Montado"); 

        } catch (Exception e) {
            exibirMensagemErro("Erro ao Montar", "", "Não foi possivel montar, verifique o arquivo");
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

    // Imprimir no terminal
    @FXML
    public void handleTERMINAL(String dado) {
        textTERMINAL.appendText(dado + "\n");
    }

}
