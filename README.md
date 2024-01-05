## Programação de Sistemas
Professor: Anderson Priebe Ferrugem. </br>
Cursos: Ciência da Computação e Engenharia de Computação. </br>
Semestre letivo: 2023/2. </br>

## Grupo Avangers
Christian Holz; Eloisa Leal Barros; Guilherme Stark; </br>
Heitor Silva Avila; Jean Carlo Silva Dos Santos; Leonardo Antonietti Ferreira; </br>
Leonardo Madruga Wille Duarte; Pedro Luis Rodrigues Porto; Rafael Silva da Silva; </br>

## Em andamento:
1. Criação do SicSim - Simulador para a máquina SIC/SIC-XE.

## Branch main (mergeado do "pe-refatorando")
1. Separação das instruções por classe (modularidade do código).
2. Virtualização da memória (banco de registradores).
3. Correção da forma no qual o código exibe dados na tela.
4. Correção de bugs, funcionando com as classes otimizadas.
5. Pacotes: Instrucoes, Interface, Maquina, Registradores, Utils.

### Pacote de instruções implementado (main/src/instrucoes):

1. ADD.java - Realiza a adição de dois operandos e armazena o resultado no acumulador.
2. ADDR.java - Realiza a adição de dois registradores e armazena o resultado no registrador especificado.
3. CLEAR.java - Limpa o conteúdo de um registrador.
4. DIV.java - Realiza a divisão de dois operandos e armazena o quociente (divisão inteira) no acumulador.
5. DIVR.java - Realiza a divisão de dois registradores e armazena o quociente (divisão inteira) no registrador especificado.
6. J.java - Desvia a execução do programa para o endereço especificado.
7. LDA.java - Carrega o conteúdo de um endereço de memória no acumulador.
8. LDB.java - Carrega o conteúdo de um endereço de memória no registrador B.
9. LDL.java - Carrega o conteúdo de um endereço de memória no registrador L.
10. LDS.java - Carrega o conteúdo de um endereço de memória no registrador S.
11. LDT.java - Carrega o conteúdo de um endereço de memória no registrador T.
12. MUL.java - Realiza a multiplicação de dois operandos e armazena o resultado no acumulador.
13. MULR.java - Realiza a multiplicação de dois registradores e armazena o resultado no registrador especificado.
14. RMO.java - Move (copia) o conteúdo de um registrador para outro registrador.
15. STA.java - Armazena o conteúdo do acumulador em um endereço de memória especificado.
16. SUB.java - Realiza a subtração de dois operandos e armazena o resultado no acumulador.
17. SUBR.java - Realiza a subtração de dois registradores e armazena o resultado no registrador especificado.

### Decodificação e Simulação (main/src/instrucoes):

1. Instrucao.java
2. Instrucoes.java

### Interface (main/src/interface):

1. Interface.fxml
2. Interface.java

### Maquina (main/src/maquina):
1. Endereco.java
2. Maquina.java
3. Memoria.java

### Registradores (main/src/registradores):
1. BancoRegistradores.java - Representa o banco de registradores da máquina simulada.
2. Registrador.java - Representa um único registrador (Orientação à Objetos).

## Branch patch-1:
1. Trabalha com um registrador por classe (A.java, B.java, F.java, L.java, ...).
2. Faz uso de métodos para decodificação de NIXBPQ e impressão das instruções lógicas simuladas.
3. Usa o conceito de aplicativo para o simulador, e não de um software propriamente dito (diretório app).

## Branch intefaceparcial:
1. Implementado preloader do FXML para carregar Interface.fxml para o simulador SicSim.
2. Implementado controladores FXML por meio de overrides (faltando as lógicas).

## Controle do grupo:
Checkpoint 1 - OK. </br>
Checkpoint 2 - Pendente. </br>
Checkpoint 3 - Pendente. </br>

<!-- Última modificação: 04/01/2024, 22:54 -->
