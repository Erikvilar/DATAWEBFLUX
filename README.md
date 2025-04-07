# ⚡ DATAWEBFLUX BACKEND SERVICES


![Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)




![Insomnia](https://img.shields.io/badge/Insomnia-black?style=for-the-badge&logo=insomnia&logoColor=5849BE)
![Dependabot](https://img.shields.io/badge/dependabot-025E8C?style=for-the-badge&logo=dependabot&logoColor=white)
![SonarLint](https://img.shields.io/badge/SonarLint-CB2029?style=for-the-badge&logo=SONARLINT&logoColor=white)



![Static Badge](https://img.shields.io/badge/version-V0.2.1-%2348A6A7)
![Static Badge](https://img.shields.io/badge/MIT-grey)


Este projeto tem como objetivo oferecer uma repleta gama de funcionalidades de serviços para o
[Dashboard view no front-end](https://github.com/Erikvilar/DASHBOARD-CRM), etse serviço possui relacionamentos complexos e completos oferecendo uma forma normalizada e acessivel de cadastro leitura removação e atualização de dados, implementado diretamente para fornecer eficiencia e ágilidade onde seu foco está no desempenho e segurança entre single-transaction || multi-transactions, ainda esta API ainda está sendo documentada e melhorada.

## 🚀 Tecnologias Utilizadas

- [Maven](https://maven.apache.org/) — Alocador de dependências.
- [Websocket](https://docs.spring.io/spring-framework/reference/web/websocket.html) — Controle de sessão de usuários e gerenciador de multiplas conexões.
- [JWT](https://jwt.is/) — Gerenciador de tokens para autenticações de transações.
- [Hibernate](https://hibernate.org/) — Persistência de dados e tradução de comandos JAVA para SQL.
- [Multipart-File](https://) — Requisição de partes de uma request que contenha arquivos.
- [Sonar-Lint](https://eslint.org/) — Padronização e linting de código
- [Map-Struct](https://mapstruct.org/) - Conversor de Data Transfer Object para Entities e vise e versa.
- [Spring-Security](https://spring.io/projects/spring-security) - Gerenciador principal de segurança entre rotas de endpoints.
- [Junit](https://junit.org/junit5/) - Ambiente de testes para aplicações JAVA.



---

## ⚙️ Funcionalidades

O sistema e capaz de fornecer cadastro entre varios tipos de relacionamenos, possui Lazy Loading facilitando que seja acessado somente se chamado e EAGER definido para entidades complexas que precisam de carregamento completo seu complemento vem com cascade PERSIST para persistência de entidades mesmo que ainda não estejam registrada via relacionamento da entidade pai.
Na parte de segurança e implementado metodos de login logout e registro, sendo controlado e mapeado.



📝 Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.