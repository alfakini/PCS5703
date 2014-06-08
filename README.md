# Agents on Mars (PCS5703)

Este repositório contém código e ferramentas utilizadas para o desenvolvimento do trabalho prático de Sistemas Multi-Agentes para o curso PCS5703. Aqui encontram-se o código do time LTI-USP (terceiro lugar na competção Agents on Mars do MAPC) e ALM-SMA (uma adaptação na camada organizacional do time LTI-USP).

## Servidor massim

```
cd massim-2013-1.4/massim/scripts
./startServer.sh
```

*Escolha a opção de torneio*

* 1: conf//2013-testmatch1.xml é a opção para rodar o time da USP contra o time da UFSC
* 0: conf//2013-sma-tests.xml é a opção para rodar o nosso time (ALM) contra o time da USP

## Visualização e Monitoramento

```
cd massim-2013-1.4/massim/scripts
./startMarsMonitor.sh
```

## Times

Exitem dois disponíveis neste projeto: LTI-USP e PCS-ALM.

Para iniciar o time PCS-ALM:

```
cd PCS-ALM
ant compile jar
ant run
```

Para iniciar o time LTI-USP:

```
cd lti-usp-2013
ant -f lti-usp-2013.xml compile jar
ant -f lti-usp-2013.xml run
```


## Começar o jogo

Após iniciar os times, volte para o terminal onde o servidor massim foi iniciado e aperte enter.
