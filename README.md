
---

# **SmiteTabList**

> Um plugin de alta performance para personalização do tablist no Minecraft, desenvolvido com as melhores práticas de engenharia de software.

O **SmiteTabList** é um plugin modular e extensível para servidores Minecraft baseados em **PaperMC**, projetado para oferecer uma experiência profissional e personalizada no tablist dos jogadores. Focado em **clean code**, **desempenho** e **manutenibilidade**, o projeto utiliza tecnologias modernas e princípios sólidos de desenvolvimento.

---

## **Recursos Principais**

- **Tablist Personalizado**: Personalize nomes, prefixos e sufixos dinamicamente com base em permissões e configurações específicas.
- **Placeholders Dinâmicos**: Integre informações como número de jogadores online, prefixos customizados e muito mais.
- **Configuração Modular**: Arquivos YAML claros e intuitivos permitem ajustes rápidos e escaláveis.
- **Sistema de Cores**: Suporte completo a formatação de cores no estilo Minecraft.
- **Integração com Permissões**: Ajuste o tablist com base em grupos ou permissões do jogador.
- **Arquitetura Escalável**: Código organizado em camadas, garantindo extensibilidade e clareza.

---

## **Requisitos**

- **Minecraft**: Compatível com a versão 1.23 ou superior.
- **Java**: Necessário Java 21 ou superior para suporte a recursos modernos da linguagem.
- **Servidor**: Recomendado PaperMC para melhor desempenho e compatibilidade.

---

## **Instalação**

1. Faça o download do arquivo `.jar` disponível na [página de releases](#).
2. Insira o arquivo na pasta `plugins` do seu servidor PaperMC.
3. Reinicie o servidor para que o plugin seja carregado.
4. Edite o arquivo `config.yml` gerado em `plugins/SmiteTabList` para ajustar as configurações.

---

## **Configuração**

O **SmiteTabList** utiliza arquivos YAML para gerenciar todas as configurações. Exemplo básico:

```yaml
tablist:
  header: "&6Bem-vindo ao servidor!"
  footer: "&7Divirta-se!"
  players:
    default: "&a{player_name}"
    admin: "&c[Admin] {player_name}"
```

Para placeholders adicionais e integrações avançadas, consulte a [documentação](#).

---

## **Arquitetura do Projeto**

O **SmiteTabList** foi desenvolvido seguindo uma abordagem orientada a objetos e os princípios **SOLID**, proporcionando um código limpo e altamente extensível.

### **Estrutura**
- **initializer/**: Configuração inicial e ciclo de vida do plugin.
- **listener/**: Eventos e interações de jogadores.
- **manager/**: Controle central de exibição e lógica do tablist.
- **provider/**: Fornecedores de dados dinâmicos como prefixos e permissões.
- **services/**: Serviços auxiliares, como placeholders e manipulação de nicks.
- **utils/**: Ferramentas de suporte, incluindo cores e manipulação de placeholders.

### **Tecnologias Utilizadas**
- **PaperMC API**: Base para interação eficiente com o Minecraft.
- **HikariCP**: Gerenciamento de conexões com bancos de dados, garantindo alta performance.
- **Lombok**: Redução de boilerplate no código.
- **Caffeine Cache**: Otimização de desempenho para dados frequentemente acessados.

---

## **Contribuição**

Contribuições são bem-vindas para melhorar o **SmiteTabList**. Para colaborar:

1. Faça um fork deste repositório.
2. Crie um branch para sua contribuição: `git checkout -b minha-feature`.
3. Faça suas alterações seguindo os padrões do projeto.
4. Envie um Pull Request explicando suas modificações.

---

## **Licença**

Este projeto é licenciado sob a [Licença MIT](LICENSE).

---

**Desenvolvido por [Haniel Cota](https://github.com/HanielCota) com foco em qualidade, desempenho e inovação.**  

Aprimore a experiência de seus jogadores com o **SmiteTabList**! 🚀

--- 
