# System Feature Flows

> Registro histórico e incremental dos fluxos internos de cada funcionalidade.
> Este documento cresce a cada nova feature implementada e **nunca tem seções removidas**.

---

## Índice

- [Visão Geral da Arquitetura](#visão-geral-da-arquitetura)
- [Convenções deste Documento](#convenções-deste-documento)
- [Feature: Observer Pattern](#feature-observer-pattern)
- [Feature: Strategy Pattern - Anti-Pattern](#feature-strategy-pattern---anti-pattern)
- [Feature: Strategy Pattern - Interface](#feature-strategy-pattern---interface)
- [Feature: Strategy Pattern - Enum](#feature-strategy-pattern---enum)
- [Feature: Builder Pattern](#feature-builder-pattern)
- [Feature: Factory Pattern](#feature-factory-pattern)
- [Feature: Singleton Pattern](#feature-singleton-pattern)
- [Feature: Decorator Pattern](#feature-decorator-pattern)
- [Feature: Proxy Pattern](#feature-proxy-pattern)
- [Feature: Command Pattern](#feature-command-pattern)

---

## Visão Geral da Arquitetura

> Projeto educacional demonstrando design patterns em Java com foco em sistema bancário.

**Padrão arquitetural:** Camadas simples por pacotes

**Estrutura de pacotes:**

```
com.banking/
├── model/           # Entidades (Account, AccountImpl)
├── observer/        # Observer Pattern
├── strategy/        # Strategy Pattern (PaymentMethod)
├── builder/         # Builder Pattern
├── factory/         # Factory Pattern
├── singleton/       # Singleton Pattern
├── decorator/       # Decorator Pattern
├── proxy/           # Proxy Pattern
├── command/         # Command Pattern
└── demo/            # Demonstrações
```

---

## Convenções deste Documento

- **Interfaces** definem contratos abstratos
- ** Eventos** são imutáveis (final fields)
- **Builders** usam API fluente (with*())
- **Factories** têm métodos estáticos para convenience
- **Singletons** usam double-checked locking

---

---

# Feature: Observer Pattern

> **Versão:** 1.0.0
> **Implementada em:** 2026-04-17
> **Status:** Concluída

---

## Resumo

Sistema de notificações que permite que qualquer componente seja notificado sobre transações (crédito/débito) em uma conta bancária, sem acoplamento direto.

**Motivação:** Notificar múltiplos sistemas (log, email, webhook) sobre transações sem que a conta conheça os detalhes.
**Resultado:** Alto desacoplamento através do padrão Observer.

---

## Design Pattern

| Pattern | Categoria | Motivação |
|---------|-----------|-----------|
| Observer | Behavioral | Desacoplamento pub/sub |

---

## Estrutura de Arquivos

| Arquivo | Descrição |
|---------|-----------|
| `model/Account.java` | Interface Observable |
| `observer/TransactionObserver.java` | Interface Observer |
| `observer/TransactionEvent.java` | Evento imutável |
| `model/AccountImpl.java` | Implementação concreta |

---

## Fluxo Principal

1. **Registro:** Cliente registra observer na conta
2. **Transação:** Conta executa credit/debit
3. **Notificação:** Conta notifica todos os observers registrados

---

## Código Relevante

```java
public interface TransactionObserver {
    void onTransaction(TransactionEvent event);
}

account.addObserver(event -> {
    System.out.println("Transaction: " + event.getType());
});
```

---

## Demonstração

```bash
mvn exec:java -Dexec.mainClass="com.banking.demo.ObserverDemo"
```

---

## ADR

| Campo | Detalhe |
|-------|---------|
| **Status** | Aceita |
| **Data** | 2026-04-17 |
| **Decisão** | Interface permite qualquer implementação |

---

---

# Feature: Strategy Pattern - Anti-Pattern

> **Versão:** 1.0.0
> **Implementada em:** 2026-04-17
> **Status:** Concluída

---

## Resumo

Implementação sem padrão Strategy, utilizando if/else encadeado.

**Motivação:** Mostrar o problema de código rígido.
**Resultado:** Demonstração de código difícil de manter.

---

## Código Relevante

```java
if (paymentMethod == 1) {
    System.out.println("Validating credit card...");
} else if (paymentMethod == 2) {
    System.out.println("Generating barcode...");
} else if (paymentMethod == 3) {
    System.out.println("Generating QR Code...");
}
```

---

## Problemas

| Problema | Impacto |
|----------|---------|
| Adicionar método | Modificar código existente |
| Código duplicado | Difícil de testar |
| Acoplamento |violação de OCP |

---

---

# Feature: Strategy Pattern - Interface

> **Versão:** 1.0.0
> **Implementada em:** 2026-04-17
> **Status:** Concluída

---

## Resumo

Implementação clássica com interface e classes concretas.

**Motivação:** Permitir intercambo de algoritmos em tempo de execução.
**Resultado:** Código flexível e testável.

---

## Estrutura

```
strategy/
├── PaymentMethod.java    # Interface
├── PaymentProcessor.java # Context
├── CreditCard.java     # Concrete Strategy
├── Boleto.java        # Concrete Strategy
└── Pix.java          # Concrete Strategy
```

---

## Código Relevante

```java
public interface PaymentMethod {
    void pay(double amount);
}

public class PaymentProcessor {
    private PaymentMethod paymentMethod;
    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void pay(double amount) {
        paymentMethod.pay(amount);
    }
}
```

---

## Demonstração

```bash
mvn exec:java -Dexec.mainClass="com.banking.demo.StrategyDemo"
```

---

## Vantagens

| Vantagem | Descrição |
|----------|------------|
| Abertura | Novo método = nova classe |
| Testabilidade | Mock fácil |
| SRP | Uma responsabilidade por classe |

---

---

# Feature: Strategy Pattern - Enum

> **Versão:** 1.0.0
> **Implementada em:** 2026-04-17
> **Status:** Concluída

---

## Resumo

Implementação moderna usando enum com Consumer.

**Motivação:** Reduzir boilerplate para casos simples.
**Resultado:** Código mais conciso.

---

## Código Relevante

```java
enum PaymentType {
    CREDIT_CARD(PaymentMethods::creditCard),
    BOLETO(PaymentMethods::boleto),
    PIX(PaymentMethods::pix);

    private Consumer<Double> strategy;
    PaymentType(Consumer<Double> strategy) { this.strategy = strategy; }
    public void pay(Double amount) { strategy.accept(amount); }
}
```

---

## Comparação

| Aspecto | Interface | Enum |
|---------|----------|-------|
| Linhas | ~60 | ~43 |
| Classes | Sim | Não |
| Estado | Suporta | Limitado |

---

---

# Feature: Builder Pattern

> **Versão:** 1.0.0
> **Implementada em:** 2026-04-17
> **Status:** Concluída

---

## Resumo

Padrão Builder para construção de objetos Account com múltiplos parâmetros opcionais.

**Motivação:** Construtores com muitos parâmetros são difíceis de usar.
**Resultado:** API fluente e legível.

---

## Estrutura

```
builder/
└── AccountBuilder.java
```

---

## Código Relevante

```java
Account account = new AccountBuilder()
    .withAccountNumber("12345-6")
    .withOwner("John Doe")
    .withInitialBalance(1000.0)
    .withDocument("123.456.789-00")
    .withEmail("john@example.com")
    .withPremium(true)
    .build();
```

---

## Demonstração

```bash
mvn exec:java -Dexec.mainClass="com.banking.demo.BuilderDemo"
```

---

## Comparação

| Abordagem | Problema |
|-----------|----------|
| Construtor 6 params | Difícil de ler |
| Múltiplos construtores | Explosão de código |
| Builder | Fluente e opcional |

---

---

# Feature: Factory Pattern

> **Versão:** 1.0.0
> **Implementada em:** 2026-04-17
> **Status:** Concluída

---

## Resumo

Factory para criação centralizada de diferentes tipos de contas.

**Motivação:** Centralizar criação, permitir novos tipos sem modificar clientes.
**Resultado:** Baixo acoplamento.

---

## Estrutura

```
factory/
└── AccountFactory.java
```

---

## Tipos de Conta

| Tipo | Saldo Inicial | Premium |
|------|---------------|---------|
| STANDARD | 0.0 | false |
| PREMIUM | 5000.0 | true |
| SAVINGS | 100.0 | false |

---

## Código Relevante

```java
Account premium = factory.createAccount(
    AccountFactory.TYPE_PREMIUM,
    "00002-2",
    "John Premium"
);

// Ou método estático
Account quick = AccountFactory.createPremiumAccount("99999-9", "User");
```

---

## Demonstração

```bash
mvn exec:java -Dexec.mainClass="com.banking.demo.FactoryDemo"
```

---

---

# Feature: Singleton Pattern

> **Versão:** 1.0.0
> **Implementada em:** 2026-04-17
> **Status:** Concluída

---

## Resumo

Singleton para garantir uma única instância do BankingService.

**Motivação:** Serviço central que conta todas as transações.
**Resultado:** Instância única garante consistência.

---

## Estrutura

```
singleton/
└── BankingService.java
```

---

## Código Relevante

```java
public class BankingService implements TransactionObserver {
    private static BankingService instance;

    private BankingService() { }

    public static synchronized BankingService getInstance() {
        if (instance == null) {
            instance = new BankingService();
        }
        return instance;
    }
}
```

---

## Demonstração

```bash
mvn exec:java -Dexec.mainClass="com.banking.demo.SingletonDemo"
```

---

## Thread Safety

| Abordagem | Prós | Contras |
|-----------|-----|--------|
| synchronized | Simples | Sincronização constante |
| Double-checked | Otimizado | Complexo |

---

---

# Feature: Decorator Pattern

> **Versão:** 1.1.0
> **Implementada em:** 2026-04-28
> **Status:** Concluída

---

## Resumo

Decorators que envolvem um `Account` para adicionar comportamentos extras sem alterar a classe original.

**Motivação:** Adicionar log e cobrança de taxa em operações sem herança ou modificação de AccountImpl.
**Resultado:** Composição flexivel de comportamentos em tempo de execução.

---

## Design Pattern

| Pattern | Categoria | Motivação |
|---------|-----------|-----------|
| Decorator | Structural | Composição de responsabilidades |

---

## Estrutura de Arquivos

| Arquivo | Descrição |
|---------|-----------|
| `decorator/AccountDecorator.java` | Classe base abstrata que delega para o wrapped |
| `decorator/LoggingAccountDecorator.java` | Loga timestamp, tipo e novo saldo em cada operação |
| `decorator/FeeChargeDecorator.java` | Cobra percentual de taxa sobre débitos |

---

## Fluxo Principal

1. **Wrap:** Cliente envolve um Account com um ou mais decorators
2. **Chamada:** Decorator executa comportamento adicional (log, taxa)
3. **Delegação:** Decorator repassa a chamada para o wrapped

---

## Código Relevante

```java
Account withFee = new FeeChargeDecorator(
    new LoggingAccountDecorator(base), 2.0
);
withFee.debit(100.0);
// [FEE] Charging fee: 2.00 (2.0%) on debit of 100.00
// [LOG] DEBIT | Account: 11111-1 | Amount: 102.0
```

---

## Demonstração

```bash
java -cp target/classes com.banking.demo.DecoratorDemo
```

---

## ADR

| Campo | Detalhe |
|-------|---------|
| **Status** | Aceita |
| **Data** | 2026-04-28 |
| **Decisão** | Classe abstrata base evita duplicação nos decorators concretos |

---

---

# Feature: Proxy Pattern

> **Versão:** 1.1.0
> **Implementada em:** 2026-04-28
> **Status:** Concluída

---

## Resumo

Protection Proxy que controla o acesso a operações de uma conta, permitindo congelar e descongelar a conta.

**Motivação:** Bloquear operações em contas suspeitas sem alterar a implementação de Account.
**Resultado:** Controle de acesso centralizado e transparente para o cliente.

---

## Design Pattern

| Pattern | Categoria | Motivação |
|---------|-----------|-----------|
| Proxy | Structural | Controle de acesso (Protection Proxy) |

---

## Estrutura de Arquivos

| Arquivo | Descrição |
|---------|-----------|
| `proxy/AccountAccessProxy.java` | Proxy de proteção com suporte a freeze/unfreeze |

---

## Fluxo Principal

1. **Operação normal:** Proxy valida estado e delega para a conta real
2. **Conta congelada:** Proxy lança IllegalStateException antes de delegar
3. **Descongelamento:** Proxy reabilita operações

---

## Código Relevante

```java
AccountAccessProxy proxy = new AccountAccessProxy(account);
proxy.freeze();
proxy.debit(100.0); // throws IllegalStateException
proxy.unfreeze();
proxy.debit(100.0); // executa normalmente
```

---

## Demonstração

```bash
java -cp target/classes com.banking.demo.ProxyDemo
```

---

## ADR

| Campo | Detalhe |
|-------|---------|
| **Status** | Aceita |
| **Data** | 2026-04-28 |
| **Decisão** | Protection Proxy escolhido por ser o caso mais representativo em banking |

---

---

# Feature: Command Pattern

> **Versão:** 1.1.0
> **Implementada em:** 2026-04-28
> **Status:** Concluída

---

## Resumo

Comandos que encapsulam operações bancárias (crédito, débito, transferência) com suporte a undo/redo via `TransactionHistory`.

**Motivação:** Desfazer transações e manter histórico auditável sem acoplamento.
**Resultado:** Operações reversíveis e rastreáveis.

---

## Design Pattern

| Pattern | Categoria | Motivação |
|---------|-----------|-----------|
| Command | Behavioral | Encapsulamento de operações com undo/redo |

---

## Estrutura de Arquivos

| Arquivo | Descrição |
|---------|-----------|
| `command/BankingCommand.java` | Interface com execute(), undo(), getDescription() |
| `command/CreditCommand.java` | Encapsula um crédito; undo faz débito |
| `command/DebitCommand.java` | Encapsula um débito; undo faz crédito |
| `command/TransferCommand.java` | Encapsula transferência entre contas; undo reverte |
| `command/TransactionHistory.java` | Pilha de comandos com execute/undo/redo |

---

## Fluxo Principal

1. **Execute:** `TransactionHistory.execute(command)` executa e empilha
2. **Undo:** `undo()` desempilha, chama `command.undo()`, empilha em undone
3. **Redo:** `redo()` desempilha de undone, re-executa e empilha em history

---

## Código Relevante

```java
TransactionHistory history = new TransactionHistory();
history.execute(new TransferCommand(alice, bob, 300.0));
history.undo();   // reverte a transferência
history.redo();   // reaplica a transferência
```

---

## Demonstração

```bash
java -cp target/classes com.banking.demo.CommandDemo
```

---

## ADR

| Campo | Detalhe |
|-------|---------|
| **Status** | Aceita |
| **Data** | 2026-04-28 |
| **Decisão** | Dois Deques separados (history/undone) permitem redo sem recomputar |

---

<p align="center">
  Este documento é um registro vivo — cada nova feature adiciona nova seção acima.
</p>