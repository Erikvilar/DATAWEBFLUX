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
