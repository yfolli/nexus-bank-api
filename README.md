# 🏦 NexusBank API

API de sistema bancário robusta desenvolvida com **Spring Boot 3** e **Java 17**. O projeto foi desenhado para simular operações bancárias reais, focando em segurança de dados, controle de concorrência e uma interface de resposta amigável para o usuário.

## 🛠️ Diferenciais do Projeto

- **Optimistic Locking (@Version):** Implementação de controle de versão nas entidades para evitar o problema de "Lost Update" (quando dois saques simultâneos tentam alterar o mesmo saldo).
- **UX de API (Interface Amigável):** As respostas de consulta foram customizadas para retornar um extrato legível em texto puro, em vez de um JSON técnico, facilitando a conferência rápida.
- **Tratamento Global de Erros:** Captura personalizada de exceções (como saldo insuficiente ou valores negativos) para retornar mensagens claras e diretas ao invés de códigos de erro genéricos.
- **Containerização com Docker:** Projeto pronto para produção, garantindo que a aplicação rode exatamente da mesma forma em qualquer ambiente através do Docker e Docker Compose.

## 🚀 Como Executar

O projeto já está configurado para subir todo o ambiente (API e configurações) automaticamente. Com o Docker instalado, execute na raiz do projeto:

```bash
docker-compose up --build

A API estará disponível em: http://localhost:8080📋 


Endpoints Principais

    Método          Endpoint                    Descrição 
      
     GET        /api/v1/accounts                 Exibe o extrato formatado com saldo das contas.
     
    POST        /api/v1/accounts/{id}/deposit    Realiza depósito (Valida valor positivo).
    
    POST        /api/v1/accounts/{id}/withdraw   Realiza saque (Valida saldo disponível).
    
    POST        /api/v1/accounts/{id}/transfer   Transferência entre contas com rollback em caso de erro.
  
  
  ⚙️ Tecnologias UtilizadasJava 17  
  
  Spring Boot 3Spring Data JPA (Persistência de dados)
  
  H2 Database (Banco em memória para testes rápidos)
  
  Bean Validation (Regras de negócio no DTO)
  
  Docker & Docker Compose (Orquestração de containers)
  
  Desenvolvido por Yan - Desenvolvedor Backend focado em qualidade de código e arquitetura.