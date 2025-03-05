# 📝 Notas de Desenvolvimento

📅 **Data:** 03/03/2025  
👨‍💻 **Projeto:** Sistema LTAD CRM

---

## 📌 Conceito: Many-to-One no JPA

> O relacionamento **Many-to-One** define que várias entidades podem estar associadas a uma única entidade pai.
> Este relacionamento dita como será a estrutura final do corpo de resposta em JSON.

### 🔹 Exemplo:

```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id") // Chave estrangeira
    private Department department;
}
```

📅 **Data:** 03/03/2025  
👨‍💻 **Projeto:** Sistema LTAD CRM

---

## 📌 Conceito: De relacionamentos em entidades e referencias em DTO

> nesta mudança sugiro trocar
--  receivingDTO seja a entidade principal.
--  itemsDTO se torne uma lista dentro de receivingDTO.
--  UsersDTO contenha contactsDTO
--  