### Programação de Sistemas
Professor: Anderson Priebe Ferrugem. </br>
Semestre letivo: 2023/2. </br>

### Grupo 8 - Avangers
1. Jean Carlo Silva Dos Santos - Coordenador.
2. Leonardo Antonietti Ferreira - Vice-coordenador.

### Back-end:
1. Eloisa Leal Barros.
2. Pedro Luis Rodrigues Porto.
3. Rafael Silva da Silva.

### Front-end:
1. Christian Holz.
3. Guilherme Stark.
4. Heitor Silva Avila.

### Documentação e controle de branches:
1. Leonardo Madruga Wille Duarte.

### Branch refatorando (ambiente de produção vigente):

1. Projeto não estava Orientado à Objetos.
2. Refatorando as classes para atender à POO:
3. Simulador.
4. Montador.
5. Processador de Macros.
6. Ligador.
7. Carregador.
8. Dependências externas à API Java importadas.

### Dependências externas:

1. Dependências externas no diretório: "/resources/javafx-sdk-21.0.2/".
2. Importação feita via arquivos JSON no diretório: "./vscode/".

### Importante:

1. Trata-se de um SIMULADOR e não de um EMULADOR do SIC/SIC-XE.
2. Para fins didáticos, adotou-se PC+1 para cada instrução lida, ao invés de PC + tamanho.
3. Diante de PC+1, a função JUMP faz o pulo para a linha especificada.

### Principais arquivos:
1. main/src/Instrucoes: Pacote de instruções SIC/SIC-XE em uma instruções por arquivo.
2. main/src/Interface: Arquivos FXML e Java que dão suporte à interface gráfica do simulador.
3. main/src/Maquina: Suporte à simulação do fluxo de execução.
4. main/src/Montador: Suporte à montagem com processamento de macros e linkador integrados.
5. main/src/Registradores: Suporte à memória do simulador SIC/SIC-XE.

### Pacote de instruções suportadas:

1. ADD.java - Realiza a adição de dois operandos e armazena o resultado no acumulador.
2. ADDR.java - Realiza a adição de dois registradores e armazena o resultado no registrador especificado.
3. CLEAR.java - Limpa o conteúdo de um registrador.
4. DIV.java - Realiza a divisão de dois operandos e armazena o quociente (divisão inteira) no acumulador.
5. DIVR.java - Realiza a divisão de dois registradores e armazena o quociente (divisão inteira) no registrador especificado.
6. J.java - Desvia a execução do programa para o endereço especificado.
7. LDA.java - Carrega o conteúdo de um endereço no acumulador.
8. LDB.java - Carrega o conteúdo de um endereço no registrador B.
9. LDL.java - Carrega o conteúdo de um endereço no registrador L.
10. LDS.java - Carrega o conteúdo de um endereço no registrador S.
11. LDT.java - Carrega o conteúdo de um endereço no registrador T.
12. MUL.java - Realiza a multiplicação de dois operandos e armazena o resultado no acumulador.
13. MULR.java - Realiza a multiplicação de dois registradores e armazena o resultado no registrador especificado.
14. RMO.java - Move (copia) o conteúdo de um registrador para outro registrador.
15. STA.java - Armazena o conteúdo do acumulador em um endereço especificado.
16. SUB.java - Realiza a subtração de dois operandos e armazena o resultado no acumulador.
17. SUBR.java - Realiza a subtração de dois registradores e armazena o resultado no registrador especificado.
18. OR.java - Realiza um OR entre o acumulador e um operando especificado, armazenando resultado no acumulador.
19. AND.java - Realiza um AND entre o acumulador e um operando especificado, armazenando resultado no acumulador.
20. SHIFTL.java - Realiza um deslocamento à esquerda no conteúdo do acumulador, inserindo zeros à direita.
21. SHIFTR.java - Realiza um deslocamento à direita no conteúdo do acumulador, inserindo zeros à esquerda.
22. COMP.java - Compara o conteúdo do acumulador com um operando, definindo uma condição de salto com base na comparação.
23. LDX.java - Carrega o conteúdo de um endereço no registrador de índice X.
24. STB.java - Armazena o conteúdo do acumulador no registrador B.
25. STS.java - Armazena o conteúdo do acumulador no registrador S.
26. STL.java - Armazena o conteúdo do acumulador no registrador L.
27. STT.java - Armazena o conteúdo do acumulador no registrador T.
28. STX.java - Armazena o conteúdo do acumulador no registrador de índice X.
29. RSUB.java - Retorna de uma sub-rotina, desviando para o endereço contido no registrador de retorno (L).
30. JEQ.java - Desvio (Jump) para o endereço se operação retorna zero (jump if result is equal to zero).
31. JGT.java - Desvio (Jump) para o endereço se operação retorna positivo (jump if address bigger than t).
32. JLT.java - Desvio (Jump) para o endereço se operação retorna negativo (jump if address less than t).

### Linha do tempo do projeto:

### Branch main:
1. Linha de desenvolvimento inicial do projeto para o simulador.
2. Protótipo de interface gráfica usando JFrame.

### Código absorvido do branch "pe-refatorando": 
1. Separação das instruções por classe.
2. Memória como coleção (banco) de objetos (registradores).

### Código absorvido do branch "interface":
1. JFrame substituído por JavaFX.
2. Correção de bugs para exibição de dados na tela.
3. Divisão do código em pacotes (packages).

### Código absorvido do branch "pe-instrucoes_refact":
1. Refatoração da camada de instruções.
2. Adaptação de meétodos para compatibilidade entre classes.

Última atualização: 11/03/2024.
