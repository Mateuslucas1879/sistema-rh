# Sistema RH - Gestão de Funcionários

## 📌 Descrição do Projeto
O **Sistema RH** é uma aplicação web desenvolvida em **Java com Spring Boot** que permite gerenciar informações de funcionários de uma empresa. O sistema possibilita cadastro, edição, listagem e exclusão de funcionários, com controle de acesso baseado em **papéis de usuário (ADMIN, RH, USUARIO)** utilizando **Spring Security**.  

O projeto utiliza **Thymeleaf** para a renderização das telas e **H2** como banco de dados em memória (pode ser facilmente migrado para MySQL ou PostgreSQL).

---

## 🛠 Funcionalidades

- **Autenticação de usuários**:
  - Login com usuários pré-cadastrados (`admin`, `rhuser`, `usuario`)
  - Proteção de rotas por papéis (ADMIN, RH, USUARIO)
- **Gestão de funcionários**:
  - Listagem completa com todos os dados: Nome, Idade, Cargo, Salário, Tipo de Contratação, Departamento, Empresa e Contato
  - Criação de novos funcionários
  - Edição de funcionários existentes
  - Exclusão de funcionários (apenas para ADMIN)
- **Interface moderna e responsiva** com Thymeleaf, utilizando gradientes, bordas arredondadas e feedback visual para botões e inputs
- **Banco de dados em memória H2** para teste rápido
- **Controle de papéis e permissões**:
  - ADMIN: total acesso
  - RH: criar, editar e visualizar funcionários
  - USUARIO: apenas visualizar funcionários

---

## 🏗 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **H2 Database**
- **Maven** para gerenciamento de dependências

---

## 🚀 Pré-requisitos

- Java 17 ou superior
- Maven 3.8+
- IDE recomendada: IntelliJ IDEA, Eclipse ou VSCode
- Navegador web moderno (Chrome, Firefox, Edge)

---

## ⚙️ Instalação e Execução

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/sistema-rh.git
cd sistema-rh
