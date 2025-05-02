## README - Bacen Service 🏛️

Este serviço simula o diretório oficial do BACEN para o gerenciamento de chaves Pix. Ele oferece endpoints para registrar, consultar, atualizar e deletar chaves Pix associadas a contas bancárias.

---

### 📌 Funcionalidades

* Registrar nova chave Pix.
* Consultar chave Pix existente.
* Atualizar status de uma chave Pix (ativa/inativa).
* Deletar chave Pix do sistema.
* Garante unicidade de chaves Pix cadastradas.

---

### 🚀 Tecnologias

* Java + Spring Boot
* JPA + H2
* Lombok
* Insomnia (testes manuais)
* WireMock (para simulações externas)

---

### ⚙️ Como executar

```bash
# Na raiz do projeto bacenService
./mvnw spring-boot:run
```

* A aplicação estará disponível em: `http://localhost:9002`

---

### 🔐 Endpoints principais

#### ✅ Criar chave Pix

```http
POST /api/bacen/chaves
```

**Exemplo de body:**

```json
{
  "chave": "fulano@pix.com",
  "ativa": true
}
```

---

#### 🔍 Buscar chave Pix

```http
GET /api/bacen/chaves/{chave}
```

---

#### ✏️ Atualizar status da chave Pix

```http
PUT /api/bacen/chaves/{chave}
```

**Exemplo de body:**

```json
{
  "chave": "fulano@pix.com",
  "ativa": false
}
```

> ⚠️ O valor da chave **não pode ser alterado**. Caso o corpo contenha uma chave diferente da URL, uma exceção personalizada é lançada.

---

#### ❌ Deletar chave Pix

```http
DELETE /api/bacen/chaves/{chave}
```

---

### 🛡️ Regras de Negócio

* Cada chave Pix é única e imutável após o registro.
* Atualizações só são permitidas no campo `ativa`.
* Operações sensíveis utilizam `@Transactional` com **rollback automático** para garantir integridade em falhas, como duplicidade de chaves.

---

### 📀 Observações

* Usa banco H2 em memória com persistência temporária.
* Valida chave duplicada no momento do cadastro.
* Integração com outros serviços via Feign (no contexto do `contaService`).
