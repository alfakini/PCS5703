

### Inicie o servidor

```
cd massim-2013-1.4/massim/scripts
./startServer.sh
```

Escolha a opção de torneio:

1: conf//2013-testmatch1.xml é a opção para rodar o time da USP contra o time da UFSC
0: conf//2013-sma-tests.xml é a opção para rodar o nosso time contra o time da USP

### Inicie o monitor

```
cd massim-2013-1.4/massim/scripts
./startMarsMonitor.sh
```

### Inicie os times

Exitem três times neste projeto: LTI-USP, UFSC e o nosso PCS-ALM.

Iniciar o LTI-USP:

```
cd lti-usp-2013
java -jar lti-usp-2013.jar
```

Iniciar o UFSC:

```
cd MAPC-UFSCTeam2013
ant runB
```

### Começar o jogo

Após iniciar os times, volte para o terminal onde o servidor foi iniciado e aperte enter.
