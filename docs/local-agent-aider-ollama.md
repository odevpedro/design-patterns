# Registro do Smoke Test do Aider + Ollama

**Data:** 2026-05-21  
**Modelo usado:** `ollama_chat/qwen2.5-coder:14b`  
**Comando executado:** `HOME=/tmp/aider-home OLLAMA_API_BASE=http://127.0.0.1:11434 aider --model ollama_chat/qwen2.5-coder:14b --no-check-update --no-analytics --no-show-model-warnings --yes-always --read README.md --file docs/local-agent-aider-ollama.md --message "Crie somente o arquivo docs/local-agent-aider-ollama.md com um registro curto do smoke test do Aider + Ollama."`
**Resultado:** O Aider conectou ao Ollama local, criou este arquivo e aplicou a edição no repositório com sucesso.

Observacao: o primeiro rascunho gerado pelo modelo veio com metadados incorretos; o conteúdo acima foi ajustado para refletir o teste real.
