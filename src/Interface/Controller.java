package src.Interface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;


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
import src.Montador.Montador;
import src.Registradores.BancoRegistradores;
import src.Exceptions.RegisterIdenfierError;
import src.Exceptions.ValueOutOfBoundError;
import src.Instrucoes.Instrucoes;
import src.Maquina.Maquina;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
    private boolean isBinaryFile;

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
        selectedFile = fileChooser.showOpenDialog(stage);
    
        // Processar o arquivo selecionado
        if (selectedFile != null) {
            handleTERMINAL("Arquivo selecionado: " + selectedFile.getName());
            try {
                byte[] fileBytes = Files.readAllBytes(selectedFile.toPath());
                isBinaryFile = true;
    
                // Verifica se o arquivo contém algum caracter que não é binário
                for (byte b : fileBytes) {
                    if ((b < 32 && b != 9 && b != 10 && b != 13) || b > 126) {
                        isBinaryFile = false;
                        break;
                    }
                }
    
                // Le o conteúdo do arquivo e exibir no textCODE
                String content = new String(fileBytes);
                textCODE.setText(content);
    
                // Atualiza o arquivo apenas quando a tecla enter for clicado (evita eventuais alteraçoes sem intencao)
                textCODE.setOnKeyPressed(keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        // Salvar o novo conteúdo no arquivo quando a tecla Enter for pressionada
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                            writer.write(textCODE.getText());
                        } catch (IOException e) {
                            exibirMensagemErro("Erro de Escrita", "", "Ocorreu um erro ao salvar as alterações no arquivo.");
                        }
                    }
                });
    
            } catch (IOException e) {
                exibirMensagemErro("Erro de Leitura", "", "Ocorreu um erro na leitura do arquivo.");
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
        if(!isAssembled || isBinaryFile){
           configurarExecução();
        }else{ // se ja esta montado, executa a maquina
            try {
                Maquina.getInstance().step();
                tableView.refresh(); //atualiza a tabela para atualizar a cor (provisório)
            } catch (Exception e) {
                exibirMensagemErro("Erro de execução", "", "Insira um código assembly ou selecione um arquivo!");
            }
        }
    }

    // ÍCONE RUN - CLIQUE
    @FXML
    void RUNimgclick(MouseEvent event) {
        if(!isAssembled || isBinaryFile){
            configurarExecução();
        }else{ // se  já está montado, executa a maquina
            try {
                Maquina.getInstance().executarPrograma();
            } catch (Exception e) {
                exibirMensagemErro("Erro de execução", "", "Insira um código assembly ou selecione um arquivo!");
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
        
        
        //colunaEndereco.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(column.getValue()) + 1));
        colunaEndereco.setCellValueFactory(cellData -> {
            ObservableList<Endereco> memoria = Memoria.getInstance().getMemoria(); // Obtém a lista de memória
            int index = memoria.indexOf(cellData.getValue()); // Obtém o índice do objeto Endereco na lista
            return new ReadOnlyObjectWrapper<>(index); // Retorna o índice como um ReadOnlyObjectWrapper
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
    // Exibir mensagem de aviso
    private void exibirMensagemAviso(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
    //Configurar execução
    public void configurarExecução(){
        
            //Se o arquivo for binario, carrega diretamente na máquina
            if(isBinaryFile){
                exibirMensagemAviso("Arquivo Selecionado é Binário","", "Carregando Diretamente na Memória");
                try {
                    Maquina.getInstance().setAquivo(selectedFile.getAbsolutePath());
                    handleTERMINAL("Memória Carregada");
                } catch (RegisterIdenfierError | ValueOutOfBoundError e) {
                    exibirMensagemErro("Erro ao Carregar Memória", "", "Não foi possivel carregar a memória!");
                }
                isAssembled=true; // defini como montado para não reentrar na configuração
            }else{
                try {
                    //Chamar o montador e passar o arquivo e configurar maquina
                    Instrucoes.inicializaInstrucoes();
                    Montador montador = new Montador(selectedFile.getAbsolutePath());

                    Maquina.getInstance().setAquivo("./testez.txt");

                    isAssembled=true; // "status" da montagem
                    handleTERMINAL("Arquivo Montado");   
                } catch (Exception e) {
                  exibirMensagemErro("Erro ao Montar", "", "Não foi possivel montar, verifique o arquivo");
                }
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
