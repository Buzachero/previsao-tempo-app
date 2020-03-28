# Previsão do Tempo App
Aplicação que disponibiliza dados de previsão de tempo para uma dada localidade.

## Tecnologias utilizadas

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Powermock](https://powermock.github.io/)
* [Log4J](https://logging.apache.org/log4j/2.x/)
* [H2](https://www.h2database.com/html/main.html)

## Requerimentos

Para realizar o build do projeto, é necessário:
* JDK 1.8
* Maven

## Parâmetros do Projeto

A configuracão da aplicação pode ser realizada em dois arquivos:
1. *application-test.properties* 
2. *application-dev.properties* 

Na execução com *application-test.properties*, as informações das cidades e dados de previsão de tempo são armazenadas diretamente no banco de dados (dados fictícios), 
ou seja, a origem desses dados é da própria aplicação, com o propósito de realização dos testes.

Na execução com *application-dev.properties*, as informações das cidades e dados de previsao de tempo são oriundas da API disponibilizada por [Climatempo](http://apiadvisor.climatempo.com.br)
ou seja, a origem desses dados é externa.

A URL externa da API de previsão do tempo também pode ser configurada, bem como o token a ser utilizado. 
Exemplo:
```
previsaotempo.url=http://apiadvisor.climatempo.com.br/api/v1/forecast/locale/<ID_DA_CIDADE>/days/15?token=<TOKEN>
previsaotempo.token=<TOKEN>
```

## Execução
Para obter dados de previsão do tempo de determinada cidade, consultando a API do [Climatempo](http://apiadvisor.climatempo.com.br):
```
GET /cidade/<id>
```
Para obter a cidade com temperatura máxima no intervalo de datas especificado e também a média de precipitação de cada cidade no mesmo intervalo:
```
GET /analise?data_inicial=dd/MM/yyyy&data_final=dd/MM/yyyy 
```


