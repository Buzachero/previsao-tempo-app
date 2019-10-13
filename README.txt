###################################
APLICACAO DE PREVISAO DE TEMPO
###################################
_______________________
BUILD E EXECUCAO

Antes de realizar o build do projeto, é necessário:
- JRE 1.8
- Maven (versao testada: 3.5.2)

Para compilar o projeto, execute o comando:
mvn clean install

Para executá-lo, entre no diretorio "target" e execute o comando:
java -jar previsao-tempo-app-1.0.0.jar

Pronto! O servidor backend estará executando na porta 8080

_______________________
PARAMETROS DO PROJETO

A configuracão da aplicação pode ser realizada em dois arquivos:
1. application-test.properties 
2. application-dev.properties 

A escolha de qual usar é configurada no arquivo application.properties, na variavel spring.profiles.active

No cenário do primeiro properties (test), as informações das cidades e dados de previsao de tempo, são armazenadas diretamente no banco de dados (dados fictícios) , 
ou seja, a origem desses dados é da própria aplicação, com o propósito de realização dos testes.

No cenário do segundo properties, as informações das cidades e dados de previsao de tempo são oriundas da api web do dominio apiadvisor.climatempo.com.br
ou seja, a origem desses dados é externa.

No properties também pode ser configurado a URL da API de previsao do tempo, bem como o token a ser utilizado. 
Exemplo:
previsaotempo.url=http://apiadvisor.climatempo.com.br/api/v1/forecast/locale/<ID_DA_CIDADE>/days/15?token=<TOKEN>
previsaotempo.token=

_______________________
REALIZACAO DOS TESTES

http://<ip_do_servidor>:<porta_servidor>
GET 
/cidade/{id} : para obter dados de previsao do tempo de determinada cidade, consultando a api web do climatempo

GET
/analise?data_inicial=dd/MM/yyyy&data_final=dd/MM/yyyy : para obter a cidade com temperatura máxima no intervalo de datas especificado e também a média de precipitação de cada cidade no mesmo intervalo.




