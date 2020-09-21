# Desafio Tinnova

*** BACKEND ***

# Banco de dados - PostgreSQL

* A aplicação utiliza o banco de dados POSTGRESQL. Para subir o banco de dados local, utilize o docker executando o comando abaixo:

    docker run --name locar -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres:11

* Não é necessário criar um database, utilize o que foi criado no comando acima ("postgres")

*** FRONTEND ***

* Após realizar o clone da aplicação, executar o comando "npm install".

* Feito isso, basta utilizar o comando "ng serve" e acessar a url http://localhost:4200
