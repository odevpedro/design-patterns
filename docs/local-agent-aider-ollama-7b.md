# Registro do Smoke Test do Aider + Ollama usando o Modelo qwen2.5-coder:7b

**Data:** 2026-05-21  
**Modelo usado:** `ollama_chat/qwen2.5-coder:7b`  
**Comando executado:** `HOME=/tmp/aider-home-7b OLLAMA_API_BASE=http://127.0.0.1:11434 aider --model ollama_chat/qwen2.5-coder:7b --no-check-update --no-analytics --no-show-model-warnings --yes-always --read README.md --file docs/local-agent-aider-ollama-7b.md --message "Crie somente o arquivo docs/local-agent-aider-ollama-7b.md com um registro curto do smoke test do Aider + Ollama usando o modelo qwen2.5-coder:7b."`
**Resultado:** O Aider conectou ao Ollama local e aplicou a edição no repositório com sucesso. O rascunho inicial saiu com um fence Markdown aberto, mas o fluxo de edição funcionou.
