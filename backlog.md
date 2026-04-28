22# Backlog — Design Patterns — Sistema Bancário

> Registro vivo do progresso do projeto. Atualizado a cada mudança de estado de uma funcionalidade.
> **Última atualização:** 2026-04-28

---

## Sobre o Projeto

Projeto educacional em Java demonstrando design patterns em um sistema bancário. Cada feature serve como insumo para justificar o uso de um pattern específico.

**Versão atual:** `1.0-SNAPSHOT`
**Repositório:** [github.com/odevpedro/design-patterns](https://github.com/odevpedro/design-patterns)
**Stack principal:** Java 17+ / Maven

---

## Legenda

| Símbolo | Significado |
|---------|-------------|
| `[ ]`   | Pendente |
| `[~]`   | Em andamento |
| `[x]`   | Concluído |
| `P0`    | Crítico — bloqueia outras features |
| `P1`    | Alta prioridade |
| `P2`    | Média prioridade |
| `P3`    | Melhoria / nice-to-have |
| `XS` `S` `M` `L` `XL` | Estimativa de complexidade |

---

## Concluídas

> Features finalizadas com suas respectivas datas de conclusão e links de referência.

| Feature | Pattern | Data | Status |
|---------|---------|------|--------|
| Sistema de notificações de transações | Observer | 2026-04-17 | Concluída |
| Strategy (anti-pattern if/else) | N/A | 2026-04-17 | Concluída |
| Strategy (interface clássica) | Strategy | 2026-04-17 | Concluída |
| Strategy (enum moderno) | Strategy | 2026-04-17 | Concluída |
| Builder Pattern | Builder | 2026-04-17 | Concluída |
| Factory Pattern | Factory | 2026-04-17 | Concluída |
| Singleton Pattern | Singleton | 2026-04-17 | Concluída |
| Refatoração estrutura profissional | Refactor | 2026-04-17 | Concluída |
| Decorator Pattern | Decorator | 2026-04-28 | Concluída |
| Proxy Pattern | Proxy | 2026-04-28 | Concluída |
| Command Pattern | Command | 2026-04-28 | Concluída |

---

## Pendentes

> Ordenadas por prioridade. Itens de P0 e P1 devem entrar em "Em Andamento" primeiro.

<!-- Nenhuma feature pendente -->

---

## Em Andamento

> Features atualmente sendo desenvolvidas. Idealmente, máximo de 2–3 itens simultâneos.

<!-- Nenhuma feature em andamento -->

---

## Bugs Conhecidos

> Problemas identificados que ainda não foram corrigidos.

| ID | Descrição | Severidade | Reportado em |
|----|-----------|------------|--------------|
| B1 | pom.xml dependência Lombok incompleta | Alta | 2026-04-17 | Corrigido |

---

## Notas & Decisões Pendentes

> Pontos em aberto que precisam de decisão antes de serem desenvolvidos.

| ID | Decisão | Status | Observação |
|----|---------|--------|-------------|
| N1 | Adicionar mais demos de patterns? | Resolvido | Decorator, Proxy e Command implementados em 2026-04-28 |
| N2 | Adicionar testes unitários? | Resolvido | 75 testes JUnit 5 implementados em 2026-04-28 |

---

## Histórico de Versões

| Versão | Data | Principais entregas |
|--------|------|---------------------|
| `1.0-SNAPSHOT` | 2026-04-17 | MVP com 5 patterns implementados |
| `1.1-SNAPSHOT` | 2026-04-28 | Decorator, Proxy e Command patterns + 75 testes JUnit 5 |