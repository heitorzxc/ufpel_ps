*****Atualizações sobre a Interface*****

*** A Interface está operacional, com necessidade de alguns ajustes e melhorias ***

** Alterações e Funcionalidades**

-Todas as saidas (System.out) foram movidas temporariamente para o campo "TERMINAL"
-A entrada do arquivo pode ser feita a partir do botão "Select File" ou do campo de texto "Code"
-Adicionado botão para imprimir a memória
-Algumas funções foram adiconadas e outras alteradas, conforme a necessidade
-Botões "Run" e "Step" estão operantes
-Falta ainda implementar o slider para controle da velocidade de execução, bem como dispor as informações nos devidos campos

**Iniciando a Aplicação**

-Para executar a Interface, rode o arquivo "TesteInterface.java"
-Selecione o arquivo de texto, ou cole o codigo no campo de texto e clique em "Load Prompt"
-Depois escolha o metodo de execução, "Run" ou "Step"

**Possiveis problemas ao utilizar o JavaFX no VSCode:

-Em alguns casos, se faz necessario a adição manual das bibliotecas ao projeto, da seguinte forma:

Após feito o download do JavaFX, bem como eventual descompactação dos arquivos. Localize a pasta "lib",
após no VSCode, abra o projeto em questão e localize a aba "JAVA PROJECTS", dentro da aba, localize a pasta "Referenced Libraries", 
adicione todos os arquivos com extenção .jar localizados na pasta "lib". 
Após isso, eventuais erros deverão desaparecer.

*** Link JavaFX ->   https://gluonhq.com/products/javafx/

-Se ao rodar o programa, surgir o erro "JavaFX runtime components are missing, and are required to run this application", será necessário
alterar o arquivo "launch.json". 

Para isso, localize o arquivo na pasta ".vscode", se não houver nenhum arquivo, vá na aba "Run" do VSCode, em "Add Configuration",
após altere/adicione o seguinte trecho:


            "type": "java",
            "name": "TesteInterface",
            "request": "launch",
            "mainClass": "src.Interface.TesteInterface",
            "projectName": "ufpel_ps_455a9f07",
            "vmArgs": "--module-path \"C:/Program Files/Java/javafx-sdk-21.0.1/lib\" --add-modules javafx.controls,javafx.fxml"

Feito isso, a aplicação deve iniciar normalmente.