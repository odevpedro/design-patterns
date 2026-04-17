# Design Patterns — Sistema Bancário

> Repositório educacional demonstrando a aplicação de design patterns em um sistema bancário, com foco em padrões de criação, estrutura e comportamento.

[![Build Status](https://img.shields.io/github/actions/workflow/status/odevpedro/design-patterns/build.yml?branch=master&style=flat-square)](https://github.com/odevpedro/design-patterns/actions)
[![License](https://img.shields.io/github/license/odevpedro/design-patterns?style=flat-square)](./LICENSE)
[![Last Commit](https://img.shields.io/github/last-commit/odevpedro/design-patterns?style=flat-square)](https://github.com/odevpedro/design-patterns/commits/master)

---

## Sobre o Projeto

Projeto educacional em Java demonstrando a implementação de design patterns em um contexto de sistema bancário. Cada feature serve como insumo para justificar o uso de um pattern específico.

**Patterns implementados:**
- **Observer** — Sistema de notificações de transações bancárias
- **Strategy** — Múltiplas implementações (anti-pattern, interface, enum)
- **Builder** — Construção fluente de objetos Account
- **Factory** — Criação centralizada de contas por tipo
- **Singleton** — Serviço bancário centralizado

---

## Stack & Arquitetura

| Camada          | Tecnologia                    |
|----------------|------------------------------|
| Runtime        | Java 17+                     |
| Build          | Maven 3.x / javac             |
| Patterns       | Creational, Behavioral        |

> Padrão arquitetural: **Camadas simples** com separação por responsabilidade (`model`, `observer`, `strategy`, `builder`, `factory`, `singleton`).

---

## Estrutura de Pastas

```
src/main/java/com/banking/
├── model/
│   ├── Account.java           # Interface Account
│   └── AccountImpl.java       # Implementação concreta
├── observer/
│   ├── TransactionObserver.java
│   └── TransactionEvent.java
├── strategy/
│   ├── PaymentMethod.java
│   ├── PaymentProcessor.java
│   ├── CreditCard.java
│   ├── Boleto.java
│   └── Pix.java
├── builder/
│   └── AccountBuilder.java
├── factory/
│   └── AccountFactory.java
├── singleton/
│   └── BankingService.java
└── demo/
    ├── ObserverDemo.java
    ├── StrategyDemo.java
    ├── BuilderDemo.java
    ├── FactoryDemo.java
    ├── SingletonDemo.java
    ├── AntiPatternDemo.java
    └── ModernStrategyDemo.java

docs/
└── system-feature-flows.md
pom.xml
```

---

## Como Executar

### Pré-requisitos

- Java 17+
- Maven 3.x (opcional)

### Compilação

```bash
mvn compile
```

### Execução dos Demos

```bash
# Demos de Patterns
mvn exec:java -Dexec.mainClass="com.banking.demo.ObserverDemo"
mvn exec:java -Dexec.mainClass="com.banking.demo.StrategyDemo"
mvn exec:java -Dexec.mainClass="com.banking.demo.BuilderDemo"
mvn exec:java -Dexec.mainClass="com.banking.demo.FactoryDemo"
mvn exec:java -Dexec.mainClass="com.banking.demo.SingletonDemo"
mvn exec:java -Dexec.mainClass="com.banking.demo.ModernStrategyDemo"
mvn exec:java -Dexec.mainClass="com.banking.demo.AntiPatternDemo"
```

---

## Design Patterns Implementados

### Behavioral Patterns

| Pattern    | Descrição                              | Arquivo                    |
|------------|----------------------------------------|---------------------------|
| Observer   | Sistema de notificações desacoplado      | `observer/*.java`         |
| Strategy   | Algoritmos intercambiáveis              | `strategy/*.java`         |

### Creational Patterns

| Pattern    | Descrição                              | Arquivo                    |
|------------|----------------------------------------|---------------------------|
| Builder    | Construção fluente de objetos          | `builder/*.java`         |
| Factory    | Criação centralizada por tipo          | `factory/*.java`         |
| Singleton  | Uma instância única                    | `sankingService.java`    |

---

## Documentação Técnica

| Documento                                         | Descrição                                    |
|---------------------------------------------------|----------------------------------------------|
| [Fluxos de Funcionalidades](./docs/system-feature-flows.md) | Fluxo interno de cada feature      |
| [Backlog](./backlog.md)                           | Status de desenvolvimento do projeto         |

---

## Status do Projeto

```
[x] Observer Pattern — sistema de notificações
[x] Strategy Pattern — 3 implementações
[x] Builder Pattern — AccountBuilder
[x] Factory Pattern — AccountFactory
[x] Singleton Pattern — BankingService
```

---

## Contributing

1. Fork o repositório
2. Crie uma branch: `git checkout -b feature/pattern-name`
3. Commit suas mudanças: `git commit -m 'feat: adiciona pattern X'`
4. Push: `git push origin feature/pattern-name`
5. Abra um Pull Request

---

## Licença

Distribuído sob a licença MIT. Veja [LICENSE](./LICENSE) para mais informações.

---

<p align="center">
  Feito para fins educacionais
</p>