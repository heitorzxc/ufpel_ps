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

1. ADD.java
2. ADDR.java
3. CLEAR.java
4. DIV.java
5. DIVR.java
6. J.java
7. LDA.java
8. LDB.java
9. LDL.java
10. LDS.java
11. LDT.java
12. MUL.java
13. MULR.java
14. RMO.java
15. STA.java
16. SUB.java
17. SUBR.java

### Decodificação e Simulação (main/src/instrucoes):

1. Instrucao.java
2. Instrucoes.java

### Interface (main/src/interface):

1. Interface.fxml
2. Interface.java

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

<!-- Última modificação: 04/01/2024, 22:42 -->
