# Golden Raspberry Awards API
API desenvolvida em Java com Spring Boot para categorizar os piores filmes da história com base no prêmio Golden Raspberry Awards.

---

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- H2 Database (em memória)
- Swagger (via SpringDoc OpenAPI)
- Maven

---

## Como baixar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/thiagomargonar/Golden_Raspberry_Awards
   
## Compile o projeto e gere o .jar:

Abra o terminal (ou prompt de comando) na raiz do seu projeto — onde está o arquivo pom.xml.

Execute o comando Maven:

bash
mvn clean package
Esse comando faz duas coisas:

clean: limpa os arquivos de build anteriores

package: compila o projeto e empacota tudo em um .jar

Verifique o resultado: Após a execução, o Maven criará um diretório target/ com o arquivo .jar gerado, por exemplo:

target/prova-0.0.1-SNAPSHOT.jar


### bash
mvn clean package -DskipTests

Como executar a aplicação 

Execute o .jar gerado:

java -jar target/prova-0.0.1-SNAPSHOT.jar

A aplicação será iniciada na porta padrão 8080.

## Acessar o Swagger
Após iniciar a aplicação, abra o navegador e acesse:

http://localhost:8080/swagger-ui.html
ou

http://localhost:8080/swagger-ui/index.html
Lá você poderá visualizar e testar todos os endpoints da API de forma interativa.

### Testes
Para rodar os testes automatizados:

### bash
mvn test

Estrutura do projeto
src/main/java: 
código-fonte da aplicação

src/test/java: 
testes automatizados

movielist.csv: 
arquivo de dados carregado automaticamente ao iniciar a aplicação

Endpoint principal
GET /worst_movies: 

retorna os produtores com os menores e maiores intervalos entre prêmios

Licença

Este projeto foi desenvolvido como parte de um desafio técnico e está disponível para fins educacionais e demonstrativos.