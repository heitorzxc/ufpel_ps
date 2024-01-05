## Programação de Sistemas
Professor: Anderson Priebe Ferrugem. </br>
Cursos: Ciência da Computação e Engenharia de Computação. </br>
Semestre letivo: 2023/2. </br>

## Grupo Avangers
1. Christian Holz.
2. Eloisa Leal Barros.
3. Guilherme Stark.
4. Heitor Silva Avila.
5. Jean Carlo Silva Dos Santos.
6. Leonardo Antonietti Ferreira.
7. Leonardo Madruga Wille Duarte.
8. Pedro Luis Rodrigues Porto.
9. Rafael Silva da Silva.

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
1. Instrucao.java - Verifica e interpreta diferentes tamanhos de instruções VÁLIDAS, dando suporte ao conjunto de atributos da máquina SIC-XE:

1. Número binário (numBin).
2. Código da instrução (opcode).
3. Tamanho do código (tamanho).
4. Instrução em hexadecimal (insHexa).
5. Endereço em binário (enderecoBinario).
6. Flags nixbpq (nixbpq).

### Interface (main/src/interface):

1. Interface.fxml - protótipo (desenho da interface) em JavaFX.
2. Interface.java - instância (OO) do protótipo JavaFX.

## Branch patch-1:
1. Trabalha com um registrador por classe.
2. Métodos para decodec do NIXBPQ e suporte das instruções.
3. Usa o conceito de aplicativo, e não de um software (app).

## Branch intefaceparcial:
1. Preloader do FXML para o simulador.
2. Implementado controladores FXML.
3. (Pendente) Implementação da lógica nos controladores FXML.

## Controle do grupo:
Checkpoint 1 - OK. </br>
Checkpoint 2 - Pendente. </br>
Checkpoint 3 - Pendente. </br>

<!-- Última modificação: 05/01/2024 -->
