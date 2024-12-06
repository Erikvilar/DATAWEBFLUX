
--> Authentication user and roles are implemented by account table
CREATE TABLE account(

    id_user INT PRIMARY KEY UNIQUE NOT NULL,
    login_user varchar(255),
    password_user varchar(255),
    role_user NOT NULL

)


