# Projeto Bankline-APP  -  Grupo SwipeUp
 ## Sumário
  - <a href="#-Introduction" >Introdução</a> 
  - <a href="#-Tecnologias" >Tecnologias</a>
  - <a href="mer">MER</a>
  - <a href="#-Configuration-used">Configuration</a>
  - <a href="#-Controller-used">Controller</a>
  - <a href="#-Model-used">Model</a>
  - <a href="#-Repository-used">Repository</a>
  - <a href="#-Service-used">Service</a>
  - <a href="#-Dependecias-used">Dependências</a>
  - <a href="#-Dependecias-used">Time</a>

##  Introdução
Projeto em grupo, relativo a Academia [Accenture ](https://www.accenture.com/br-pt) 4.0 Banck end Java, ministrado pela [Gama Academy](https://www.gama.academy/).

## Objetivo
- Criar uma solução da BankLine para a realização de Crédito, Débito e Transferência
- Prover um webservices com arquitetura REST para atender as demandas do site. 
- Cadastro de usuário terá os campos login, senha, nome e cpf. 
- O campo login pode conter caracteres que representam cpf, telefone ou apelido com até 20 caracteres.
- Quando for realizado o cadastro, criar uma conta com saldo zero para o usuário.
- Esta conta representa uma conta de receitas, despesas e transferências.
- Cadastro de Plano de Contas - finalidade das receitas, despesas e
transferências.
- Ao cadastrar o usuário deverão ser criados quatro planos de conta padrão: RECEITAS / DESPESAS / TRANSFERENCIAS - estes planos de contas não poderão ser alterados.
  - Receitas: Planos de Contas que aumenta o saldo da conta: SALARIO, VENDAS e etc ..
  - Despesas: Planos de Contas que decrementa o saldo da conta: LUZ,
INTERNET, RESTAURANTE e etc.
- O usuário poderá realizar transferências para outros usuários:
Recomendamos na entidade Conta salvar o login do usuário para facilitar a busca por login de destino.
- Registro de lançamentos de receitas, despesas e transferências contendo:Data lançamento, Conta, Plano de Conta, Valor e Descrição. Em caso de transferência entre contas precisa informar a conta de destino.
- Extrato dos Lançamentos por Conta e Período de Datas inicial e final - Dashboard
- Gestão de Saldo das Contas.
- Disponibilizar uma API Rest que deverá disponibilizar o serviço de
autenticação e autorização para usuários
- Disponibilizar a documentação dos recursos do Websservices REST.

## Tecnologias utilizadas
- Java 11
- Spring JPA
- PostgreSQL

# MER
<div>
  <img src="https://raw.githubusercontent.com/swipeup-dev/bankline-api/2a78a2c9a5092d226d9fb630f30dbfd90c1ffdfe/images/MER_bankline.svg"/>
</div>
## Estrutura
O Projeto foi idalizado utilizando arquitetura MVC.

**Model:** Os packages foram divididos internamente no model para uma melhor coesão. <br>
  - dto: Aqu definimos Data transfer object
  - entity: No model definimos as classes dos objetos utilizados no sistema.
  - enums: Aqui definimos as enumerações;
  - exception: Definimos exceções exclusivas do model
  
**Repository:** Acesso a dados por JPA<br>
**Service:** Regras de negócio para manipulação dos Models<br>
**Controller:** Onde definimos a interação do frontend com a API por meio da definição dos endpoints<br>
**Exception:** Onde definimos nossas exceções<br>
**Doc:** Configurações do Swagger para documentar a API<br>
**Configuration:** Configurações de segurança do Spring Security e JWT.

A Estrutura completa está representado no seguinte esquema abaixo:

└── banklineapi/ <br> 
    ├── BanklineApiApplication.java <br>
    ├── configuration/<br>
    │   └── TesteConfiguration.java<br>
    ├── controller/<br>
    │   ├── exceptions/<br>
    │   │   ├── handler/<br>
    │   │   │   ├── EntityRequirementExceptionHandler.java<br>
    │   │   │   ├── ExistingRecordExceptionHandler.java<br>
    │   │   │   ├── HandleException.java<br>
    │   │   │   ├── HttpMessageNotReadableExceptionHandler.java<br>
    │   │   │   ├── InvalidArgumentExceptionHandler.java<br>
    │   │   │   ├── InvalidAuthenticationExceptionHandler.java<br>
    │   │   │   ├── PersistenceExceptionHandler.java<br>
    │   │   │   └── RecordNotFoundExceptionHandler.java<br>
    │   │   └── StandardError.java<br>
    │   ├── LancamentoController<br>
    │   ├── PlanoContaController.java<br>
    │   └── UsuarioController.java<br>
    ├── doc/<br>
    │   └── SwaggerConfig.java<br>
    ├── model/<br>
    │   ├── dto/<br>
    │   │   ├── AtualizadorSenhaDto.java<br>
    │   │   ├── LancamentoDto.java<br>
    │   │   ├── LoginDto.java<br>
    │   │   ├── NovaSenhaDto.java<br>
    │   │   ├── PlanoContaDto.java<br>
    │   │   ├── SessaoDto.java<br>
    │   │   └── UsuarioDto.java<br>
    │   ├── entity/<br>
    │   │   ├── Conta.java<br>
    │   │   ├── Lancamento.java<br>
    │   │   ├── PlanoConta.java<br>
    │   │   └── Usuario.java<br>
    │   ├── enums/<br>
    │   │   └── TipoTransacao.java<br>
    │   └── exception/<br>
    │       ├── EntityRequirementException.java<br>
    │       ├── ExistingRecordException.java<br>
    │       ├── InvalidArgumentException.java<br>
    │       ├── InvalidAuthenticationException.java<br>
    │       └── RecordNotFoundException.java<br>
    ├── repository/<br>
    │   ├── ContaRepository.java<br>
    │   ├── LancamentoRepository.java<br>
    │   ├── PlanoContaRepository.java<br>
    │   └── UsuarioRepository.java<br>
    ├── security/<br>
    │   ├── JWTAuthorizationFilter.java<br>
    │   ├── JWTConstants.java<br>
    │   └── WebSecurityConfig.java<br>
    ├── service/<br>
    │   ├── ContaService.java<br>
    │   ├── LancamentoService.java<br>
    │   ├── PlanoContaService.java<br>
    │   └── UsuarioService.java<br>
    └── util/<br>
        └── Validator.java <br>
## Configuration
- TesteConfiguration.java
Arquivo de configuração do perfil de testes para validar a relação entre entidades/tabelas
## Controller
- controller/execeptions
  - controller/execeptions/handler
    - EntityRequirimentExceptionHandler.java
    - ExistingRecordExceptionHandler.java
    - HandleException.java
    - HttpMessageNotReadableExceptionHandler.java
    - InvalidArgumentExceptionHandler.java
    - InvalidAuthenticationExceptionHandler.java
    - PersistenceExceptionHandler.java
    - RecordNotFoundExceptionHandlerjava
  - StandarError.java 
- LancamentoController.java
- PlanoContaController.java
- UsuarioController.java 
## doc
- SwaggerConfig.java

## Model
  ### dto
    - AtualizadorSenhaDto.java
    - LancamentoDto.java
    - LoginDto.java
    - NovaSenhaDto.java
    - PlanoContaDto.java
    - SessaoDto.java
    - UsuarioDto.java
  ### entity
  - Conta.java
  - Lancamento.java  
  - PlanoConta.java
  - Usuario.java      
  ### enums
  - TipoTransacao.java
 ### exception
  - EntityRequirementException.java
  - ExistingRecordException.java
  - InvalidArgumentException.java
  - InvalidAuthenticationException.java
  - RecordNotFoundException.java
## repository
- ContaRepository.java
- LancamentoRepository.java
- PlanoContaRepository.java
- UsuarioRepository.java         
## Security
- JWTAuthorizationFilter.java
- JWTConstants.java
- WebSecurityConfig.java
## Service
- ContaService.java
- LancamentoService.java
- PlanoContaService.java
- UsuarioService.java
## Test
BanklineApiApplicationTests.java 
## Dependências
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- spring-boot-devtools
- h2 database (para testes me memória)
- postgresql
- spring-boot-starter-test
- spring-boot-starter-validation
- hibernate-validator
- validation-api
- spring-boot-starter-security
- jjwt
- springfox-swagger2
- springfox-swagger-ui

# 💻 Time SwipeUp
- Fabiana `(Development)` [LinkedIn](https://www.linkedin.com/in/fabiana-pereira-xavier/) [Github]()
- Matheus `(Development)`  [LinkedIn](https://www.linkedin.com/in/matheus-marcena/)  [Github]()
- Misael `(Development)`  [LinkedIn](https://www.linkedin.com/in/misael-nascimento-57681659/)   [Github](https://github.com/MisaelSivuca)
- Thomas `(Development)`  [LinkedIn](https://www.linkedin.com/in/twsm000/)  [Github](https://github.com/twsm000)