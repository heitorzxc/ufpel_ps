## Programação de Sistemas
Professor: Anderson Priebe Ferrugem. </br>
Cursos: Ciência da Computação e Engenharia de Computação. </br>
Semestre letivo: 2023/2. </br>

## Grupo Avangers
Christian Holz; Eloisa Leal Barros; Guilherme Stark; </br>
Heitor Silva Avila; Jean Carlo Silva Dos Santos; Leonardo Antonietti Ferreira; </br>
Leonardo Madruga Wille Duarte; Pedro Luis Rodrigues Porto; Rafael Silva da Silva; </br>

## Em andamento:
Criação do SicSim - Simulador para a máquina SIC/SIC-XE.

### Branch main:
1. Conversores.java: Facilita a manipulação da exibição de inteiros, hexadecimais e strings entre interfaces e métodos.
2. Enderecos.java: Faz os getters e setters de endereços, valores e índices decimais, binários e hexadecimais para o simulador.
3. Instrucao.java: Verifica a compatibilidade da instrução com o simulador e faz a lógica de interpretação de diferentes tamanhos de instrução.
4. Interface.java: Pseudo-interface criada com a bliblioteca Swing (JPanel, JBox, JSlider) para apresentação do simulador (obsoleta).
5. Interface.fxml: Criado usando JavaFX, ~ainda não absorvido para uma interface final~ absorvido pelo branch pe-refatorando.
6. Maquina.java: Inicialização lógica dos registradores da máquina, da lógica do intepretador e da lógica de saída para simulação.
7. Memoria.java: Representa um buffer de instruções (obtidas linha a linha) para a interpretação da máquina por métodos de decodificação e read/write na memória.
8. Registrador.java: Representa um registrador simples do simulador que é capaz de trabalhar com números decimais e binários.

### Branch patch-1:
1. Trabalha com um registrador por classe (A.java, B.java, F.java, L.java, ...).
2. Faz uso de métodos para decodificação de NIXBPQ e impressão das instruções lógicas simuladas.
3. Usa o conceito de aplicativo para o simulador, e não de um software propriamente dito (diretório app).

### Branch intefaceparcial:
1. Implementado preloader do FXML para carregar Interface.fxml para o simulador SicSim.
2. Implementado controladores FXML por meio de overrides (faltando as lógicas).

### Branch pe-refatorando:
1. Merge de main com pe-refatorando.
2. Separação das instruções por classe (modularidade do código).
3. Implmentação de registradores (Commit "A").
4. Correção de impressões vazias de memória (feita).
5. Correção de intentação (feita).
6. Refatoração do código (feita).

### Controle do grupo:
Checkpoint 1 - OK. </br>
Checkpoint 2 - Pendente. </br>
Checkpoint 3 - Pendente. </br>
