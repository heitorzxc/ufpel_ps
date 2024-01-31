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

### Documentação e controle de branches:
1. Heitor Silva Avila.
2. Leonardo Madruga Wille Duarte.

### Cronograma de atividades:
1. Simulador para SIC/SIC-XE (OK).
2. Montador para SIC/SIC-SE (OK).
3. Processador de macros para SIC/SIC-SE (Em andamento).

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

### Resumo do projeto:
1. main/src: Arquivo main que que está instanciando o montador.
2. main/src/Instrucoes: Pacote de instruções SIC/SIC-XE em uma instruções por arquivo.
3. main/src/Interface: Arquivos fxml e java que dão suporte à interface gráfica do simulador.
4. main/src/Maquina: Suporte à decodificação do endereço, instância da máquina virtual e banco de registradores.
5. main/src/Montador: Suporte à execução do montador de dois passos.
6. main/src/Registradores: Suporte à modelagem de um registrador e à instância do banco de registradores.
7. main/stc/Utils: Conversores entre sistemas de numeração e tipos de dados.

### Aos desenvolvedores:
1. main/src: O arquivo main.java permite a instância do simulador ou montador, bastando alterar as linhas comentadas.

### Instruções adicionadas até primeiro checkpoint:
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

### Instruções adicionadas após o primeiro checkpoint:
1. OR.java - Realiza um OR entre o acumulador e um operando especificado, armazenando resultado no acumulador.
2. AND.java - Realiza um AND entre o acumulador e um operando especificado, armazenando resultado no acumulador.
3. SHIFTL.java - Realiza um deslocamento à esquerda no conteúdo do acumulador, inserindo zeros à direita.
4. SHIFTR.java - Realiza um deslocamento à direita no conteúdo do acumulador, inserindo zeros à esquerda.
5. COMP.java - Compara o conteúdo do acumulador com um operando, definindo uma condição de salto com base na comparação.
6. LDX.java - Carrega o conteúdo de um endereço no registrador de índice X.
7. STB.java - Armazena o conteúdo do acumulador no registrador B.
8. STS.java - Armazena o conteúdo do acumulador no registrador S.
9. STL.java - Armazena o conteúdo do acumulador no registrador L.
10. STT.java - Armazena o conteúdo do acumulador no registrador T.
11. STX.java - Armazena o conteúdo do acumulador no registrador de índice X.
12. RSUB.java - Retorna de uma sub-rotina, desviando para o endereço contido no registrador de retorno (L).
13. JEQ.java - Desvio (Jump) para o endereço se operação retorna zero (jump if result is equal to zero).
14. JGT.java - Desvio (Jump) para o endereço se operação retorna positivo (jump if address bigger than t).
15. JLT.java - Desvio (Jump) para o endereço se operação retorna negativo (jump if address less than t).

### Atualizações futuras:
1. Correções na interface e back-end do simulador.
2. Correções na interface e back-end do montador.
3. Desenvolvimento do processador de macros.

Última atualização: 31/01/2024.