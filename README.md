# Desafio Concrete
Aplicação para cadastro e controle de acesso de usuarios, desenvolvida como resultado do desafio para seleção da [Concrete](https://www.concrete.com.br/).

# EndPoints
Todos o endpoints recebem requisições POST e repondem o perfil do usuario consultado em formato JSON.

## Cadastro

* Endpoint
```
.../logon/cadastro
```
* Formato da requisicao:
```
{
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "987654321",
            "ddd": "21"
        }, 
        {
            "number": "987654322",
            "ddd": "21"
        }
    ]
}
```
## Login
* Endpoint
```
.../logon/login
```
* Formato da requisicao:
```
{
   "email": "joao@silva.org",
   "password": "hunter2"
}
```

## Perfil
* Endpoint
```
.../logon/perfil
```
* Formato da requisicao:
```
{
    "usrId": "3a4bc224-93b7-466b-8572-bdfc305e9cb4",
    "token": "df16887d-e153-4d78-b7a7-0156a4d5d376"
}
```


# Autor
* [Erivan Sousa](https://github.com/erivansousa)
