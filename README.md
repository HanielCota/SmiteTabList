
---

# **SmiteTabList**
[![Java](https://img.shields.io/badge/Java-21%2B-orange)](https://openjdk.org/projects/jdk/21/)

O **SmiteTabList** é um plugin avançado e personalizável para servidores Minecraft. Ele permite gerenciar e customizar o tablist dos jogadores com funcionalidades poderosas e intuitivas, como placeholders dinâmicos, integração com sistemas de permissões, e personalização de cores. Feito para quem deseja transformar o tablist em um diferencial no servidor!

---

## **🎮 Recursos**
- **Customização Completa**: Altere o tablist de forma dinâmica com nomes, prefixos e sufixos personalizados.
- **Placeholders Dinâmicos**: Exiba informações como número de jogadores online ou mensagens customizadas.
- **Configuração YAML**: Arquivos de configuração fáceis de usar para ajustes rápidos.
- **Integração com Permissões**: Personalize o tablist com base em permissões do jogador.
- **Sistema de Cores**: Adicione códigos de cores para criar um visual único e vibrante.
- **Desempenho Otimizado**: Totalmente compatível com PaperMC para máxima eficiência.

---

## **📋 Requisitos**
- **Minecraft**: Versão 1.23 ou superior.
- **Java**: Versão 21 ou superior.
- **PaperMC**: Recomendado para melhor compatibilidade.

---

## **📥 Instalação**
1. Faça o download do plugin na [página de releases](#).
2. Coloque o arquivo `.jar` na pasta `plugins` do seu servidor PaperMC.
3. Reinicie o servidor.
4. Configure o plugin no arquivo `config.yml` gerado na pasta `plugins/SmiteTabList`.

---

## **⚙️ Configuração**
O arquivo `config.yml` permite personalizar completamente o tablist:
```yaml
# Exemplo de Configuração
tablist:
  header: "&6Bem-vindo ao servidor!"
  footer: "&7Divirta-se com amigos!"
  players:
    default: "&a{player_name}"
    admin: "&c[Admin] {player_name}"
```

Para mais placeholders, consulte a [documentação completa](#).

---

## **🛠 Desenvolvimento**
### **Estrutura do Projeto**
- **initializer/**: Configuração inicial do plugin.
- **listener/**: Eventos de jogadores.
- **manager/**: Gerenciamento de exibições e nicks.
- **provider/**: Fornecedores de prefixos e configurações.
- **services/**: Serviços de placeholders.
- **utils/**: Utilitários para cores, placeholders e configuração.

### **Tecnologias**
- **Java 21**: Código moderno e performático.
- **PaperMC API**: Para maior eficiência em servidores Minecraft.
- **Princípios Clean Code**: Código limpo e escalável.

---

## **📚 Contribuição**
Contribuições são bem-vindas! Siga os passos abaixo para colaborar:
1. Faça um fork deste repositório.
2. Crie um branch para sua feature ou correção: `git checkout -b minha-feature`.
3. Faça o commit de suas alterações: `git commit -m 'Adiciona nova feature'`.
4. Envie o push para o branch: `git push origin minha-feature`.
5. Abra um Pull Request.

---

## **🔗 Links**
- **[Documentação](#)** (em breve)
- **[Página de Releases](#)**

---

## **📝 Licença**
Este projeto é licenciado sob a [MIT License](LICENSE).

---

Transforme o tablist do seu servidor com o **SmiteTabList**! 🚀  
**Desenvolvido com ❤️ por [Haniel Cota](https://github.com/HanielCota).**

--- 
